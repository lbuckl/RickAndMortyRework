package com.molchanov.core.di.network

import com.molchanov.core.di.android.AndroidDependenciesModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidDependenciesModule::class,
        NetworkModule::class
    ]
)

interface NetworkComponent: NetworkProvider {

    companion object {

        fun create(): NetworkProvider {
            return DaggerNetworkComponent.builder().build()
        }
    }
}