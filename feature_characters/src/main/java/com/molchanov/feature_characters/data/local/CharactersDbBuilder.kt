package com.molchanov.feature_characters.data.local

import android.content.Context
import androidx.room.Room
import javax.inject.Inject

class CharactersDbBuilder @Inject constructor(private val context: Context) {

    private val characterEpisodeDB: CharactersDB? = null

    fun getCharacterEpisodeDB(): CharactersDB {
        return characterEpisodeDB ?:
            Room.databaseBuilder(
                context,
                CharactersDB::class.java,
                CharactersDB.CHAR_EPISODE_DB_NAME
            ).build()
    }
}