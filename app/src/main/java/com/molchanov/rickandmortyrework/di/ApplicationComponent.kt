package com.molchanov.rickandmortyrework.di

import com.molchanov.core.di.ApplicationProvider
import com.molchanov.rickandmortyrework.RickAndMortyApp
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent: ApplicationProvider {

    companion object {

        fun init(app: RickAndMortyApp): ApplicationProvider {
            return DaggerApplicationComponent.factory().create()
        }
    }

    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }
}