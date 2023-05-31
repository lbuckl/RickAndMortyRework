package com.molchanov.feature_characters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.databinding.FragmentBaseVmBinding
import com.molchanov.coreui.fragment.BaseVmFragment
import com.molchanov.feature_characters.di.CharactersComponent


class CharactersFragment :
    BaseVmFragment<FragmentBaseVmBinding, CharactersAppState, CharactersViewModel>() {

    companion object {
        val instance = CharactersFragment()

        const val FRAGMENT_TAG = "CharactersFragment_IdentificationTag"
    }

    /*private val onRVItemClickListener = object : CharactersRVAdapter.OnListItemClickListener {
        override fun onItemClick(data: Character) {

            viewModel.getDetailsInfo(data)

            binding.abBaseIcFilter.vision(View.GONE)
            binding.abBaseIcSearch.vision(View.GONE)
        }
    }

    private val rvAdapter = CharactersRVAdapter(onRVItemClickListener)*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.abBaseTvHeader.text = resources.getString(com.molchanov.coreui.R.string.characters)

        initRvAdapters()

        initViewModel()

        initButtons()

        return binding.root
    }

    private fun initRvAdapters() {

        /*binding.include.rvPagination.adapter = pagRvAdapter

        binding.rvBase.adapter = rvAdapter

        binding.rvBase.layoutManager = GridLayoutManager(this.context, 2)*/
    }

    private fun initViewModel() {

        /*viewModel = ViewModelProvider(this, vmFactory)[CharactersViewModel::class.java]

        App.app.getAppComponent().inject(viewModel)

        initBaseViewModel()

        viewModel.getLastSuccessStateValue()?.let {
            pagRvAdapter.replaceData(it.pageNum, it.pageActual)
        }*/
    }

    private fun initButtons() {

        initButtonBack()

        initButtonSearch()

        initButtonFilter()
    }

    private fun initButtonBack() {

        /*binding.abBaseIcBack.setOnClickListener {
            router.deleteFragment(
                childFragmentManager,
                binding.flBaseContainer.id,
                CharacterDetailsFragment.instance,
                CharacterDetailsFragment.FRAGMENT_TAG
            )

            viewModel.getLastSuccess()

            with(binding) {
                flBaseContainer.vision(View.GONE)

                abBaseIcBack.vision(View.GONE)

                abBaseIcFilter.vision(View.VISIBLE)

                abBaseIcSearch.vision(View.VISIBLE)
            }
        }*/
    }

    private fun initButtonSearch() {

        /*binding.abBaseIcSearch.setOnClickListener {
            //Создаём диологовое окно
            val searchDialogFragment = SearchDialogFragment.instance

            //Инициализируем прослушку
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {

                    viewModel.searchData(searchWord)
                }
            })
            //отображаем диологовое окно
            searchDialogFragment.show(parentFragmentManager, SearchDialogFragment.FRAGMENT_TAG)
        }*/
    }

    private fun initButtonFilter() {
        /*binding.abBaseIcFilter.setOnClickListener {
            //Создаём диологовое окно
            val charactersFilterDialogFragment = CharactersFilterDialogFragment.instance

            //Инициализируем прослушку
            charactersFilterDialogFragment.setOnFilterClickListener(object :
                CharactersFilterDialogFragment.OnFilterClickListener {
                override fun onClick(filterWords: CharacterFilterData) {

                    viewModel.getFilteredData(filterWords)
                }
            })
            //отображаем диологовое окно
            charactersFilterDialogFragment.show(
                parentFragmentManager,
                SearchDialogFragment.FRAGMENT_TAG
            )
        }*/
    }

    override fun renderData(state: CharactersAppState) {

        /*when (state) {
            is CharactersAppState.Success -> {

                binding.errorLayout.vision(View.GONE)

                with(state.data) {
                    rvAdapter.replaceData(this.characterList)

                    pagRvAdapter.replaceData(pageNum, pageActual)
                }
            }
            is CharactersAppState.SuccessCharacter -> {

                successDetailsScenario()

                val bundle = Bundle()
                bundle.putParcelable(
                    CharacterDetailsFragment.FRAGMENT_MESSAGE_TAG,
                    state.data
                )

                if (
                    childFragmentManager.findFragmentByTag(
                        CharacterDetailsFragment.FRAGMENT_TAG
                    ) == null
                )
                    router.replaceFragmentWithMessage(
                        childFragmentManager,
                        binding.flBaseContainer.id,
                        CharacterDetailsFragment.instance,
                        CharacterDetailsFragment.FRAGMENT_TAG,
                        bundle
                    )

            }
            is CharactersAppState.Error -> {

                errorScenario()

                Snackbar.make(binding.root, state.errorMsg, Snackbar.LENGTH_LONG)
                    .setAnchorView(binding.btnReload)
                    .setTextColor(resources.getColor(R.color.black, requireContext().theme))
                    .show()
            }
            is CharactersAppState.Loading -> {

                loadingScenario(state.isLoading)
            }
        }*/
    }

    override fun inject(applicationProvider: ApplicationProvider) {
        CharactersComponent.init(applicationProvider).inject(this@CharactersFragment)
    }
}