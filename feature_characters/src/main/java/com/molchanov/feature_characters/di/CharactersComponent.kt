package com.molchanov.feature_characters.di

import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.di.RouterModule
import com.molchanov.feature_characters.ui.CharactersFragment
import dagger.Component

@Component(
    dependencies = [
        ApplicationProvider::class
    ],
    modules = [
        RouterModule::class,
        CharactersViewModelModule::class
    ]
)
interface CharactersComponent {

    companion object {

        fun init(
            applicationProvider: ApplicationProvider
        ): CharactersComponent {
            return DaggerCharactersComponent.factory()
                .create(applicationProvider)
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            applicationProvider: ApplicationProvider
        ): CharactersComponent
    }

    fun inject (fragment: CharactersFragment)
}