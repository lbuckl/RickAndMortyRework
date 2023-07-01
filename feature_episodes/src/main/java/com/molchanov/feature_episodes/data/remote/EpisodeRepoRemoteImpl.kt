package com.molchanov.feature_episodes.data.remote

import com.molchanov.core.domain.repository.requests.IRemoteRequest
import com.molchanov.domain.EPISODE_HEADER
import com.molchanov.domain.EPISODE_NAME
import com.molchanov.domain.episodes.EpisodePage
import com.molchanov.feature_episodes.data.EpisodeFilterData
import com.molchanov.feature_episodes.mappers.DtoDomainMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Класс реализующий взаимодействие с API персонажей Rick and Morty
 */
class EpisodeRepoRemoteImpl @Inject constructor(
    private val api: EpisodesApi,
    private val dtoDomainMapper: DtoDomainMapper
) : IRemoteRequest<Int, String, EpisodeFilterData, EpisodePage> {

    //Функция получения данных по тегу "Страница"
    override fun getData(requestData: Int): Single<EpisodePage> {
        return api.getEpisodes(requestData.toString())
            .map { dto ->
                dtoDomainMapper.episodesDTOtoDomainPage(dto, requestData)
            }
    }

    //Функция запрашивает данные и мапит только те данные, которые ищет пользователь
    override fun getSearchedData(requestData: Int, searchWord: String): Single<EpisodePage> {
        return api.getEpisodes(requestData.toString())
            .map { dto ->
                dtoDomainMapper.episodesDTOtoDomainPageSearched(dto, requestData, searchWord)
            }
    }

    //Функция запрашивает данные по фильтру
    override fun getFilteredData(
        requestData: Int,
        filter: EpisodeFilterData
    ): Single<EpisodePage> {

        val queryMap = getEpisodeFilterQueryMap(filter)

        return api.getFilteredEpisodes(queryMap).map { dto ->
            dtoDomainMapper.episodesDTOtoDomainPage(dto, requestData)
        }
    }

    private fun getEpisodeFilterQueryMap(filter: EpisodeFilterData): Map<String, String> {

        val queryMap = mutableMapOf<String, String>()

        filter.name?.let { queryMap[EPISODE_NAME] = it }
        filter.episode?.let { queryMap[EPISODE_HEADER] = it }

        return queryMap
    }
}