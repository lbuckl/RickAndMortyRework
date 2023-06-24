package com.molchanov.repository.data.characters

import androidx.room.Database
import androidx.room.RoomDatabase
import com.molchanov.repository.data.characters.entity.CharacterDetailsEntity
import com.molchanov.repository.data.characters.entity.CharacterPageEntity

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