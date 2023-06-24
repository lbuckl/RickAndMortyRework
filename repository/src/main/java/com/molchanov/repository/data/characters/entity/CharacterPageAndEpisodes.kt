package com.molchanov.repository.data.characters.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Класс собирает в себе данные в связной БД
 * состоящей из CharacterPageEntity и CharacterEpisodeEntity
 */
data class CharacterPageAndDetails(

    @Embedded
    val characterPage: CharacterPageEntity,

    @Relation(
        parentColumn = "id",
        entity = CharacterDetailsEntity::class,
        entityColumn = "detailId"
    )
    val characterDetailsEntities: List<CharacterDetailsEntity>
)