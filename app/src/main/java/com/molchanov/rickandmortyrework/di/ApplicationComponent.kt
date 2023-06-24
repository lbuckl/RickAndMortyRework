package com.molchanov.rickandmortyrework.di

import com.molchanov.core.di.AndroidDependenciesProvider
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.core.di.android.AndroidDependenciesComponent
import com.molchanov.core.di.network.NetworkComponent
import com.molchanov.core.di.network.NetworkProvider
import com.molchanov.core.di.viewmodel.ViewModelFactoryModule
import com.molchanov.rickandmortyrework.RickAndMortyApp
import dagger.Component

@Component(
    dependencies = [
        AndroidDependenciesProvider::class,
        NetworkProvider::class
    ],
    modules = [
        ViewModelFactoryModule::class,
        ApplicationModule::class
    ]
)
interface ApplicationComponent: ApplicationProvider {

    companion object {

        fun init(app: RickAndMortyApp): ApplicationProvider {

            val androidDependenciesProvider = AndroidDependenciesComponent.create(app)
            val networkProvider = NetworkComponent.create()

            return DaggerApplicationComponent.factory()
                .create(
                    androidDependenciesProvider,
                    networkProvider
                )
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            androidDependenciesProvider: AndroidDependenciesProvider,
            networkProvider: NetworkProvider
        ): ApplicationComponent
    }
}