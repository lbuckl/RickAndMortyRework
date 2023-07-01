package com.molchanov.feature_episodes.data.local

import com.molchanov.core.domain.repository.requests.ILocalRequest
import com.molchanov.domain.episodes.EpisodePage
import com.molchanov.feature_episodes.data.EpisodeFilterData
import com.molchanov.feature_episodes.data.getRoomFormat
import com.molchanov.feature_episodes.mappers.DaoDomainMapper
import com.molchanov.repository.data.episodes.EpisodesDbBuilder
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Класс реализующий взаимодействие с БД эпизодов
 */
class EpisodeRepoLocalImpl @Inject constructor(
    private val dbExist: EpisodesDbBuilder,
    private val mapper: DaoDomainMapper
) : ILocalRequest<Int, EpisodeFilterData, String, EpisodePage> {

    private val dataBase = dbExist.getEpisodeDB()

    //Функция получения данных по тегу "Страница"
    override fun getData(requestData: Int): Single<EpisodePage> {

        dbExist

        return dataBase.getDAO().queryPageAndCharacters(requestData)
            .map { data ->
                mapper.daoEpisodesAndCharactersToDomain(data)
            }
    }

    //Функция запрашивает данные и мапит только те данные, которые ищет пользователь
    override fun getSearchedData(requestData: Int, searchWord: String): Single<EpisodePage> {

        return dataBase.getDAO().queryPageAndCharacters(requestData)
            .map { data ->
                mapper.daoEpisodesAndCharactersToDomainSearch(data, searchWord)
            }
    }

    //Функция запрашивает данные по фильтру
    override fun getFilteredData(
        requestData: Int,
        filter: EpisodeFilterData
    ): Single<EpisodePage> {

        val request = filter.getRoomFormat()

        return dataBase.getDAO().queryFilteredPageAndCharacters(
            request.name!!,
            request.episode!!
        ).map { data ->
            mapper.daoEpisodesAndCharactersToDomain(data)
        }
    }

    //Сохранение данных в БД Room
    override fun saveData(data: EpisodePage, key: Int) {

        //Реализовано сохранение для связного списка
        data.episodeList.forEach { ep ->

            dataBase.getDAO().insertEpisode(
                mapper.episodeDomainToDao(ep, data.pageActual, data.pageNum)
            )

            dataBase.getDAO().insertEpisodesDetails(
                mapper.episodeDetailsDomainToDao(ep)
            )
        }
    }
}