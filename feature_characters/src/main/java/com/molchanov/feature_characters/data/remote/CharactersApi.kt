package com.molchanov.feature_characters.data.remote

import com.molchanov.feature_characters.data.remote.dto.CharacterDetails
import com.molchanov.feature_characters.data.remote.dto.CharacterDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 * Интерфейс для запроса из API персонажей Rick and Morty
 */
interface CharactersApi {

    @GET
    fun getCharacter(@Url url: String): Single<CharacterDetails>

    @GET("api/character")
    fun getCharacters(@Query("page") page: String): Single<CharacterDto>

    @GET("api/character")
    fun getFilteredCharacters(
        @QueryMap options: Map<String, String>
    ): Single<CharacterDto>
}