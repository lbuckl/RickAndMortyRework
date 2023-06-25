package com.molchanov.repository.data.locations

import android.content.Context
import androidx.room.Room
import javax.inject.Inject

class LocationsDbBuilder @Inject constructor(private val context: Context) {

    private val characterEpisodeDB: LocationDB? = null

    fun getCharacterEpisodeDB(): LocationDB {
        return characterEpisodeDB ?:
            Room.databaseBuilder(
                context,
                LocationDB::class.java,
                LocationDB.LOCATION_CHAR_DB_NAME
            ).build()
    }
}