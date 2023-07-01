package com.molchanov.feature_episodes.di

import com.molchanov.core.di.ApplicationProvider
import com.molchanov.core.di.network.NetworkStatusModule
import com.molchanov.coreui.di.RouterModule
import dagger.Component

@Component(
    dependencies = [
        ApplicationProvider::class
    ],
    modules = [
        RouterModule::class,
        //CharactersViewModelModule::class,
        EpisodesApiModule::class,
        EpisodesRepositoryModule::class,
        NetworkStatusModule::class
    ]
)
interface EpisodesComponent {

    companion object {
        fun init(
            applicationProvider: ApplicationProvider
        ): EpisodesComponent {
            return DaggerEpisodesComponent.factory()
                .create(applicationProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): EpisodesComponent
    }

    //fun inject (fragment: CharactersFragment)
}