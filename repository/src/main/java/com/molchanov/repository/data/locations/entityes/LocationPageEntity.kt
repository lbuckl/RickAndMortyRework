package com.molchanov.repository.data.locations.entityes

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Классы для харения данных в БД Room
 */
@Entity(tableName = "LocationPageTab")
data class LocationPageEntity(
    @PrimaryKey
    val id: Long,
    val pageNum: Int,
    val pageActual: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val url: String
)

@Entity(
    tableName = "EpisodeDetailsTab",

    foreignKeys = [ForeignKey(
        entity = LocationPageEntity::class,
        parentColumns = ["id"],
        childColumns = ["detailId"],
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["detailId", "character"]
)
data class LocationDetailsEntity(
    val detailId: Long,
    val character: String
)