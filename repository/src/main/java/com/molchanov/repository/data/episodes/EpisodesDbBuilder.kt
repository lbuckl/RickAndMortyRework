package com.molchanov.repository.data.episodes

import android.content.Context
import androidx.room.Room
import javax.inject.Inject

class EpisodesDbBuilder @Inject constructor(private val context: Context) {

    private val characterEpisodeDB: EpisodeDB? = null

    fun getCharacterEpisodeDB(): EpisodeDB {
        return characterEpisodeDB ?:
            Room.databaseBuilder(
                context,
                EpisodeDB::class.java,
                EpisodeDB.EPISODE_CHAR_DB_NAME
            ).build()
    }
}