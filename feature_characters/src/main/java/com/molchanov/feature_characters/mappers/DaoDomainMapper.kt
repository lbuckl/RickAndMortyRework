package com.molchanov.feature_characters.mappers

import com.molchanov.core.utils.findWordInText
import com.molchanov.feature_characters.data.local.entity.CharacterDetailsEntity
import com.molchanov.feature_characters.data.local.entity.CharacterPageAndDetails
import com.molchanov.feature_characters.data.local.entity.CharacterPageEntity
import com.molchanov.feature_characters.domain.Character
import com.molchanov.feature_characters.domain.CharacterPage
import javax.inject.Inject

class DaoDomainMapper @Inject constructor() {

    fun characterDomainToDao(
        character: com.molchanov.feature_characters.domain.Character,
        pageActual: Int,
        pages: Int
    ): CharacterPageEntity =
        CharacterPageEntity(
            character.id.toLong(),
            pages,
            pageActual,
            character.name,
            character.spec,
            character.status,
            character.gender,
            character.imgUrl,
            character.url
        )

    fun characterDetailsDomainToDao(character: Character): List<CharacterDetailsEntity> {

        val result: MutableList<CharacterDetailsEntity> = mutableListOf()

        character.episodes.forEach {
            result.add(
                CharacterDetailsEntity(
                    character.id.toLong(),
                    it
                )
            )
        }

        return result.toList()
    }

    fun daoCharacterAndEpisodesToDomain(characterPageAndEpisodes: List<CharacterPageAndDetails>): CharacterPage =
        CharacterPage(
            characterPageAndEpisodes[0].characterPage.pageNum,
            characterPageAndEpisodes[0].characterPage.pageActual,
            characterPageAndEpisodes.map {
                Character(
                    it.characterPage.id.toInt(),
                    it.characterPage.name,
                    it.characterPage.spec,
                    it.characterPage.status,
                    it.characterPage.gender,
                    it.characterPage.imgUrl,
                    it.characterDetailsEntities.map { ep ->
                        ep.episode
                    },
                    it.characterPage.url
                )
            }
        )

    fun daoCharacterAndEpisodesToDomainSearch(
        characterPageAndEpisodes: List<CharacterPageAndDetails>,
        searchWord: String
    ): CharacterPage {

        val result = mutableListOf<CharacterPageAndDetails>()

        characterPageAndEpisodes.forEach {
            if (findWordInText(it.characterPage.name, searchWord) ||
                findWordInText(it.characterPage.status, searchWord) ||
                findWordInText(it.characterPage.gender, searchWord) ||
                findWordInText(it.characterPage.spec, searchWord)
            ) result.add(it)
        }

        return CharacterPage(
            characterPageAndEpisodes[0].characterPage.pageNum,
            characterPageAndEpisodes[0].characterPage.pageActual,
            result.toList().map {
                Character(
                    it.characterPage.id.toInt(),
                    it.characterPage.name,
                    it.characterPage.spec,
                    it.characterPage.status,
                    it.characterPage.gender,
                    it.characterPage.imgUrl,
                    it.characterDetailsEntities.map { ep ->
                        ep.episode
                    },
                    it.characterPage.url
                )
            }
        )
    }

    fun daoCharacterToDomainDetail(character: CharacterPageEntity): Character {
        return Character(
            character.id.toInt(),
            character.name,
            character.spec,
            character.status,
            character.gender,
            character.imgUrl,
            listOf(),
            character.url
        )
    }
}