package com.molchanov.coreui.di

import androidx.lifecycle.ViewModelProvider
import com.molchanov.coreui.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class VMFactoryModule {

    //@Singleton
    @Binds
    abstract fun getVMFactory(vmFactory: ViewModelFactory): ViewModelProvider.NewInstanceFactory
}