package com.molchanov.rickandmortyrework.di

import com.molchanov.core.di.AndroidDependenciesProvider
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.core.di.android.AndroidDependenciesComponent
import com.molchanov.rickandmortyrework.RickAndMortyApp
import dagger.Component

@Component(
    dependencies = [
        AndroidDependenciesProvider::class
    ],
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent: ApplicationProvider {

    companion object {

        fun init(app: RickAndMortyApp): ApplicationProvider {

            val androidDependenciesProvider = AndroidDependenciesComponent.create(app)

            return DaggerApplicationComponent.factory()
                .create(
                    androidDependenciesProvider
                )
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            androidDependenciesProvider: AndroidDependenciesProvider
        ): ApplicationComponent
    }
}