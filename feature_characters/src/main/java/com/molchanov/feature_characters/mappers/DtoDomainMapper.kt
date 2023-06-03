package com.molchanov.repository.utils

import com.molchanov.core.utils.findWordInText
import com.molchanov.feature_characters.data.remote.dto.CharacterDetails
import com.molchanov.feature_characters.data.remote.dto.CharacterDto
import com.molchanov.feature_characters.domain.Character
import com.molchanov.feature_characters.domain.CharacterPage
import javax.inject.Inject

class DtoDomainMapper @Inject constructor() {

    fun charactersDTOtoDomainPage(characters: CharacterDto, page: Int): CharacterPage =
        CharacterPage(
            pageNum = characters.info.pages,
            pageActual = page,
            characters.results.map {
                charactersDTOtoDomain(it)
            }
        )

    fun charactersDTOtoDomain(character: CharacterDetails): Character =
        Character(
            id = character.id,
            name = character.name,
            spec = character.species,
            status = character.status,
            gender = character.gender,
            imgUrl = character.image,
            episodes = character.episode.map {
                it
            },
            url = character.url
        )

    fun charactersDTOtoDomainPageSearched(
        characters: CharacterDto,
        page: Int,
        searchWord: String
    ): CharacterPage {

        val result = mutableListOf<CharacterDetails>()

        characters.results.forEach {
            if (
                findWordInText(it.name, searchWord) ||
                findWordInText(it.status, searchWord) ||
                findWordInText(it.gender, searchWord) ||
                findWordInText(it.species, searchWord)
            ) {
                result.add(it)
            }
        }

        return CharacterPage(
            pageNum = characters.info.pages,
            pageActual = page,
            result.toList().map {
                charactersDTOtoDomain(it)
            }
        )
    }
}