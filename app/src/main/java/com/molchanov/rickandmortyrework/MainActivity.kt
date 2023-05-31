package com.molchanov.rickandmortyrework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.molchanov.core.di.App
import com.molchanov.coreui.router.IRouter
import com.molchanov.feature_characters.ui.CharactersFragment
import com.molchanov.rickandmortyrework.base.BaseActivity
import com.molchanov.rickandmortyrework.databinding.ActivityMainBinding
import com.molchanov.rickandmortyrework.di.MainActivityComponent
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject lateinit var router: IRouter

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private fun inject(app: App) {
        MainActivityComponent.init(app.getApplicationProvider())
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}