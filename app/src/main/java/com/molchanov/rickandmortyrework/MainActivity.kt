package com.molchanov.rickandmortyrework

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.molchanov.core.di.App
import com.molchanov.core.domain.network.INetworkStatus
import com.molchanov.coreui.router.IRouter
import com.molchanov.feature_characters.ui.CharactersFragment
import com.molchanov.rickandmortyrework.base.BaseActivity
import com.molchanov.rickandmortyrework.databinding.ActivityMainBinding
import com.molchanov.rickandmortyrework.di.MainActivityComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var router: IRouter

    @Inject
    lateinit var networkStatus: INetworkStatus

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private fun inject(app: App) {
        MainActivityComponent.init(app.getApplicationProvider())
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initNetworkChecker()
    }

    override fun attachBaseContext(newBase: Context) {
        inject(newBase.applicationContext as App)
        super.attachBaseContext(newBase)
    }

    override fun onStart() {
        super.onStart()

        initMenuListener()
    }

    private fun initMenuListener() {

        binding.bnvMain.menu.let { menu ->

            binding.bnvMain.setOnItemSelectedListener { item ->

                //TODO navigate to fragments

                return@setOnItemSelectedListener true
            }
        }
    }

    private fun navigateTo(fragment: Fragment, tag: String) {
        with(binding) {
            router.addFragment(
                supportFragmentManager,
                container.id,
                fragment,
                tag
            )
        }
    }

    override fun addMainFragment() {
        navigateTo(
            CharactersFragment.instance,
            CharactersFragment.FRAGMENT_TAG
        )
    }

    private fun initNetworkChecker() {
        disposable.add(
            networkStatus.isOnline()
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { someElement ->
                        if (!someElement) Toast.makeText(this, "connection lost", Toast.LENGTH_LONG)
                            .show()
                    },
                    {
                        Toast.makeText(this, "Unknown network state", Toast.LENGTH_LONG)
                            .show()
                    }
                )
        )
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}