package com.molchanov.repository.data.episodes.entityes

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Классы для харения данных в БД Room
 */
@Entity(tableName = "EpisodePageTab")
data class EpisodePageEntity(
    @PrimaryKey
    val id: Long,
    val pageNum: Int,
    val pageActual: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val url: String
)

@Entity(
    tableName = "EpisodeDetailsTab",

    foreignKeys = [ForeignKey(
        entity = EpisodePageEntity::class,
        parentColumns = ["id"],
        childColumns = ["detailsId"],
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["detailsId", "character"]
)
data class EpisodeDetailsEntity(
    val detailsId: Long,
    val character: String
)