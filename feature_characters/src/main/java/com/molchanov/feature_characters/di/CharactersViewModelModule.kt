package com.molchanov.feature_characters.di

import androidx.lifecycle.ViewModel
import com.molchanov.core.di.viewmodel.ViewModelKey
import com.molchanov.feature_characters.ui.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CharactersViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel
}