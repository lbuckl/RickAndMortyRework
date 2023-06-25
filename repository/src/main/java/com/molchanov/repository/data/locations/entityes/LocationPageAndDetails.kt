package com.molchanov.repository.data.locations.entityes

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Класс собирает в себе данные в связной БД
 * состоящей из EpisodePageEntity и EpisodeDetailsEntity
 */
data class LocationPageAndDetails(

    @Embedded
    val locationPage: LocationPageEntity,

    @Relation(
        parentColumn = "id",
        entity = LocationDetailsEntity::class,
        entityColumn = "detailId"
    )
    val locationDetailsEntities: List<LocationDetailsEntity>
)