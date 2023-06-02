package com.molchanov.core.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.molchanov.core.data.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
