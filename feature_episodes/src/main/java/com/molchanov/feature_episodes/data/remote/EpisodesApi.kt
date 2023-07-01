package com.molchanov.feature_episodes.data.remote

import com.molchanov.feature_episodes.data.remote.dto.EpisodeDetails
import com.molchanov.feature_episodes.data.remote.dto.EpisodesDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 * Интерфейс для запроса из API эпизодов Rick and Morty
 */
interface EpisodesApi {

    @GET
    fun getEpisode(@Url url: String): Single<EpisodeDetails>

    @GET("api/episode")
    fun getEpisodes(@Query("page") page: String): Single<EpisodesDto>

    @GET("api/episode")
    fun getFilteredEpisodes(
        @QueryMap options: Map<String, String>
    ): Single<EpisodesDto>
}