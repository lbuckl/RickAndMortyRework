package com.molchanov.repository.data.characters.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterPageTab")
data class CharacterPageEntity(
    @PrimaryKey
    val id: Long,
    val pageNum: Int,
    val pageActual: Int,
    val name: String,
    val spec: String, //Вид
    val status: String,
    val gender: String,
    val imgUrl: String,
    val url: String
)

@Entity(
    tableName = "CharacterDetailsTab",

    foreignKeys = [ForeignKey(
        entity = CharacterPageEntity::class,
        parentColumns = ["id"],
        childColumns = ["detailId"],
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["detailId", "episode"]
)
data class CharacterDetailsEntity(
    val detailId: Long,
    val episode: String
)