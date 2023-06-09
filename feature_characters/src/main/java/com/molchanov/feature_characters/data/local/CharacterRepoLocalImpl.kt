package com.molchanov.feature_characters.data.local

import com.molchanov.core.domain.repository.requests.ILocalRequest
import com.molchanov.domain.characters.CharacterPage
import com.molchanov.feature_characters.data.CharacterFilterData
import com.molchanov.feature_characters.data.getRoomFormat
import com.molchanov.feature_characters.mappers.DaoDomainMapper
import com.molchanov.repository.data.characters.CharactersDbBuilder
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharacterRepoLocalImpl @Inject constructor(
    private val dbExist: CharactersDbBuilder,
    private val mapper: DaoDomainMapper
) : ILocalRequest<Int, CharacterFilterData, String, CharacterPage> {

    private val dataBase = dbExist.getCharacterDB()

    //Функция получения данных по тегу "Страница"
    override fun getData(requestData: Int): Single<CharacterPage> {
        return dataBase.getDAO().queryPageAndEpisodes(requestData)
            .map { data ->
                mapper.daoCharacterAndEpisodesToDomain(data)
            }
    }

    override fun getSearchedData(requestData: Int, searchWord: String): Single<CharacterPage> {
        return dataBase.getDAO().queryPageAndEpisodes(requestData)
            .map { data ->
                mapper.daoCharacterAndEpisodesToDomainSearch(data, searchWord)
            }
    }

    override fun getFilteredData(
        requestData: Int,
        filter: CharacterFilterData
    ): Single<CharacterPage> {

        val request = filter.getRoomFormat()

        return dataBase.getDAO().queryFilteredPageAndEpisodes(
            request.name!!,
            request.status!!,
            request.species!!,
            request.gender!!
        ).map { data ->
            mapper.daoCharacterAndEpisodesToDomain(data)
        }
    }


    override fun saveData(data: CharacterPage, key: Int) {
        data.characterList.forEach { char ->

            dataBase.getDAO().insertCharacter(
                mapper.characterDomainToDao(char, data.pageActual, data.pageNum)
            )

            dataBase.getDAO().insertCharacterDetails(
                mapper.characterDetailsDomainToDao(char)
            )
        }
    }
}