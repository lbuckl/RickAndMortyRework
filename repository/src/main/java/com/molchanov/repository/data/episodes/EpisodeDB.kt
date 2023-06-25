package com.molchanov.repository.data.episodes

import androidx.room.Database
import androidx.room.RoomDatabase
import com.molchanov.repository.data.episodes.entityes.EpisodeDetailsEntity
import com.molchanov.repository.data.episodes.entityes.EpisodePageEntity

/**
 * БД эпизодов
 */
@Database(
    entities = [
        EpisodePageEntity::class,
        EpisodeDetailsEntity::class
    ], version = 1
)

abstract class EpisodeDB : RoomDatabase() {

    companion object {
        const val EPISODE_CHAR_DB_NAME = "EPISODE_CHARACTER_DB"
    }

    abstract fun getDAO(): EpisodeDAO
}