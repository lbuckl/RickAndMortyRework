package com.molchanov.feature_characters.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.molchanov.feature_characters.data.local.entity.CharacterDetailsEntity
import com.molchanov.feature_characters.data.local.entity.CharacterPageEntity

/**
 * БД персонажей
 */
@Database(
    entities = [
        CharacterPageEntity::class,
        CharacterDetailsEntity::class
    ], version = 1
)

abstract class CharactersDB : RoomDatabase() {

    companion object {
        const val CHAR_EPISODE_DB_NAME = "CHARACTER_EPISODE_DB"
    }

    abstract fun getDAO(): CharactersDAO
}