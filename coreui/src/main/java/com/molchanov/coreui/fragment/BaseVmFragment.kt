package com.molchanov.coreui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.molchanov.core.data.viewmodel.ViewModelFactory
import com.molchanov.core.di.App
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.core.domain.viewmodel.AppState
import com.molchanov.coreui.R
import com.molchanov.coreui.databinding.FragmentBaseVmBinding
import com.molchanov.coreui.pagination.PaginationRVAdapter
import com.molchanov.coreui.router.IRouter
import com.molchanov.coreui.utils.PROGRESS_DELAY
import com.molchanov.coreui.utils.vision
import com.molchanov.coreui.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * Базовый класс для фрагментов с ViewModel
 */
abstract class BaseVmFragment<T : ViewBinding, AS : AppState, VM : BaseViewModel<AS>> :
    BaseFragment<FragmentBaseVmBinding>() {

    @Inject
    lateinit var router: IRouter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract val viewModel: VM

    var localLoading = false

    private val onPagRVItemClickListener = object : PaginationRVAdapter.OnListItemClickListener {

        override fun onItemClick(data: Pair<Int, Boolean>) {
            binding.errorLayout.vision(View.GONE)

            viewModel.getData(data.first)
        }
    }

    val pagRvAdapter = PaginationRVAdapter(onPagRVItemClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        inject((requireActivity().application as App).getApplicationProvider())

        initBaseButtons()

        initLoading()

        return binding.root
    }

    private fun initBaseButtons() {

        initButtonReload()
    }

    private fun initButtonReload() {
        binding.btnReload.setOnClickListener {
            viewModel.reloadData()
        }
    }

    private fun initLoading() {
        with(binding.ivLoading) {
            Glide.with(this)
                .load(R.drawable.gif_rm_dance)
                .placeholder(R.drawable.ic_no_photo_vector)
                .into(this)
        }
    }

    abstract fun inject(applicationProvider: ApplicationProvider)

    abstract fun renderData(state: AS)

    override fun getViewBinding(): FragmentBaseVmBinding {
        return FragmentBaseVmBinding.inflate(layoutInflater)
    }

    /**
     * Функция позволяет не отображать прогресс бар
     * если загрузка идёт менее 0,5 сек
     */
    private fun loadingShowDelay() {

        Thread {
            Thread.sleep(PROGRESS_DELAY)

            if (localLoading) {
                requireActivity().runOnUiThread {
                    binding.ivLoading.vision(View.VISIBLE)
                }
            }
        }.start()
    }

    fun successDetailsScenario() {
        binding.errorLayout.vision(View.GONE)

        binding.flBaseContainer.vision(View.VISIBLE)

        binding.abBaseIcBack.vision(View.VISIBLE)
    }

    fun errorScenario() {
        binding.errorLayout.vision(View.VISIBLE)

        binding.abBaseIcBack.vision(View.VISIBLE)
    }

    fun loadingScenario(loadState: Boolean) {
        with(binding.ivLoading) {

            if (loadState) {

                localLoading = true

                loadingShowDelay()
            } else {
                localLoading = false

                vision(View.GONE)
            }
        }
    }
}