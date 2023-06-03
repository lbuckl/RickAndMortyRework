package com.molchanov.feature_characters.data.remote

import com.molchanov.core.domain.repository.requests.IRemoteRequest
import com.molchanov.feature_characters.data.CharacterFilterData
import com.molchanov.feature_characters.domain.*
import com.molchanov.repository.utils.DtoDomainMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Класс реализующий взаимодействие с API персонажей Rick and Morty
 */
class CharacterRepoRemoteImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val dtoDomainMapper: DtoDomainMapper
) : IRemoteRequest<Int, String, CharacterFilterData, CharacterPage> {

    //Функция получения данных по тегу "Страница"
    override fun getData(requestData: Int): Single<CharacterPage> {
        return charactersApi.getCharacters(requestData.toString())
            .map { dto ->
                dtoDomainMapper.charactersDTOtoDomainPage(dto, requestData)
            }
    }

    //Функция запрашивает данные и мапит только те данные, которые ищет пользователь
    override fun getSearchedData(requestData: Int, searchWord: String): Single<CharacterPage> {
        return charactersApi.getCharacters(requestData.toString())
            .map { dto ->
                dtoDomainMapper.charactersDTOtoDomainPageSearched(dto, requestData, searchWord)
            }
    }

    //Функция запрашивает данные по фильтру
    override fun getFilteredData(
        requestData: Int,
        filter: CharacterFilterData
    ): Single<CharacterPage> {

        val queryMap = getCharacterFilterQueryMap(filter)

        return charactersApi.getFilteredCharacters(queryMap).map { dto ->
            dtoDomainMapper.charactersDTOtoDomainPage(dto, requestData)
        }
    }


    private fun getCharacterFilterQueryMap(filter: CharacterFilterData): Map<String, String> {

        val queryMap = mutableMapOf<String, String>()

        filter.name?.let { queryMap[CHARACTER_NAME] = it }
        filter.status?.let { queryMap[CHARACTER_STATUS] = it }
        filter.species?.let { queryMap[CHARACTER_SPECIES] = it }
        filter.gender?.let { queryMap[CHARACTER_GENDER] = it }

        return queryMap
    }
}