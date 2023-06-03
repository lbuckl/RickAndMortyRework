package com.molchanov.feature_characters.di

import com.molchanov.core.domain.network.IRickAndMortyRetrofit
import com.molchanov.feature_characters.data.remote.CharactersApi
import dagger.Module
import dagger.Provides

@Module
class CharactersApiModule {

    @Provides
    fun provideCharactersApi(retrofit: IRickAndMortyRetrofit): CharactersApi {
        return retrofit.getRickAndMortyRetrofit().create(CharactersApi::class.java)
    }
}