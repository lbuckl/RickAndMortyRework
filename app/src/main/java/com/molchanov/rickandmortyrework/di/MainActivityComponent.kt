package com.molchanov.rickandmortyrework.di

import com.molchanov.core.di.ApplicationProvider
import com.molchanov.core.di.network.NetworkStatusModule
import com.molchanov.coreui.di.RouterModule
import com.molchanov.rickandmortyrework.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RouterModule::class,
        NetworkStatusModule::class
    ],
    dependencies = [
        ApplicationProvider::class,
    ]
)
interface MainActivityComponent {

    companion object {
        fun init(applicationProvider: ApplicationProvider): MainActivityComponent {
            return DaggerMainActivityComponent.factory()
                .create(applicationProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}