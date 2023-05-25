package com.molchanov.rickandmortyrework.di

import com.molchanov.core.di.ApplicationProvider
import com.molchanov.rickandmortyrework.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
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