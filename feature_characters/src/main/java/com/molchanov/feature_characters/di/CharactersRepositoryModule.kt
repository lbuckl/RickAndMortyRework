package com.molchanov.feature_characters.di

import com.molchanov.core.domain.repository.IRepository
import com.molchanov.core.domain.repository.requests.ILocalRequest
import com.molchanov.core.domain.repository.requests.IRemoteRequest
import com.molchanov.feature_characters.data.CharacterFilterData
import com.molchanov.feature_characters.data.CharactersRepoImpl
import com.molchanov.feature_characters.data.local.CharacterRepoLocalImpl
import com.molchanov.feature_characters.data.remote.CharacterRepoRemoteImpl
import com.molchanov.feature_characters.domain.CharacterPage
import com.molchanov.feature_characters.ui.CharactersAppState
import dagger.Binds
import dagger.Module

@Module
interface CharactersRepositoryModule {

    @Binds
    fun getCharacterRepoImpl(
        charactersRepoImpl: CharactersRepoImpl
    ): IRepository<CharactersAppState, CharacterFilterData>

    @Binds
    fun getCharacterRepoRemoteImpl(
        characterRepoRemoteImpl: CharacterRepoRemoteImpl
    ): IRemoteRequest<Int, String, CharacterFilterData, CharacterPage>

    @Binds
    fun bindCharactersRepoLocalImpl(charactersRepoLocalImpl: CharacterRepoLocalImpl):
            ILocalRequest<Int, CharacterFilterData, String, CharacterPage>

    @Binds
    fun getCharactersFilterData(characterFilterData: CharacterFilterData): CharacterFilterData

}