package com.molchanov.feature_characters.data.local

import com.molchanov.core.domain.repository.requests.ILocalRequest
import com.molchanov.feature_characters.data.CharacterFilterData
import com.molchanov.feature_characters.data.getRoomFormat
import com.molchanov.feature_characters.domain.CharacterPage
import com.molchanov.feature_characters.mappers.DaoDomainMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Класс реализующий взаимодействие с БД персонажей
 */
class CharacterRepoLocalImpl @Inject constructor(
    private val dbExist: CharactersDbBuilder,
    private val mapper: DaoDomainMapper
) : ILocalRequest<Int, CharacterFilterData, String, CharacterPage> {

    //Функция получения данных по тегу "Страница"
    override fun getData(requestData: Int): Single<CharacterPage> {
        return dbExist.getCharacterEpisodeDB().getDAO().queryPageAndEpisodes(requestData)
            .map { data ->
                mapper.daoCharacterAndEpisodesToDomain(data)
            }
    }

    //Функция запрашивает данные и мапит только те данные, которые ищет пользователь
    override fun getSearchedData(requestData: Int, searchWord: String): Single<CharacterPage> {
        return dbExist.getCharacterEpisodeDB().getDAO().queryPageAndEpisodes(requestData)
            .map { data ->
                mapper.daoCharacterAndEpisodesToDomainSearch(data, searchWord)
            }
    }

    //Функция запрашивает данные по фильтру
    override fun getFilteredData(
        requestData: Int,
        filter: CharacterFilterData
    ): Single<CharacterPage> {

        val request = filter.getRoomFormat()

        return dbExist.getCharacterEpisodeDB().getDAO().queryFilteredPageAndEpisodes(
            request.name!!,
            request.status!!,
            request.species!!,
            request.gender!!
        ).map { data ->
            mapper.daoCharacterAndEpisodesToDomain(data)
        }
    }

    //Сохранение данных в БД Room
    override fun saveData(data: CharacterPage, key: Int) {

        //Реализовано сохранение для связного списка
        data.characterList.forEach { char ->

            dbExist.getCharacterEpisodeDB().getDAO().insertCharacter(
                mapper.characterDomainToDao(char, data.pageActual, data.pageNum)
            )

            dbExist.getCharacterEpisodeDB().getDAO().insertCharacterDetails(
                mapper.characterDetailsDomainToDao(char)
            )
        }
    }
}