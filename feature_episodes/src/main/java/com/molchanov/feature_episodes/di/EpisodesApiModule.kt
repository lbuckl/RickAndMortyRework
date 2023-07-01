package com.molchanov.feature_episodes.di

import com.molchanov.core.domain.network.IRickAndMortyRetrofit
import com.molchanov.feature_episodes.data.remote.EpisodesApi
import dagger.Module
import dagger.Provides

@Module
class EpisodesApiModule {

    @Provides
    fun provideCharactersApi(retrofit: IRickAndMortyRetrofit): EpisodesApi {
        return retrofit.getRickAndMortyRetrofit().create(EpisodesApi::class.java)
    }
}