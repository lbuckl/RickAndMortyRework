package com.molchanov.repository.data.characters

import android.content.Context
import androidx.room.Room
import javax.inject.Inject

class CharactersDbBuilder @Inject constructor(private val context: Context) {

    private val characterDB: CharactersDB? = null

    fun getCharacterDB(): CharactersDB {
        return characterDB ?:
            Room.databaseBuilder(
                context,
                CharactersDB::class.java,
                CharactersDB.CHAR_EPISODE_DB_NAME
            ).build()
    }
}