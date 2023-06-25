package com.molchanov.repository.data.episodes.entityes

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Класс собирает в себе данные в связной БД
 * состоящей из EpisodePageEntity и EpisodeDetailsEntity
 */
data class EpisodePageAndDetails(

    @Embedded
    val episodePage: EpisodePageEntity,

    @Relation(
        parentColumn = "id",
        entity = EpisodeDetailsEntity::class,
        entityColumn = "detailsId"
    )
    val episodeCharacterEntities: List<EpisodeDetailsEntity>
)