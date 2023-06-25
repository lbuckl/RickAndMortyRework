package com.molchanov.repository.data.locations

import androidx.room.Database
import androidx.room.RoomDatabase
import com.molchanov.repository.data.locations.entityes.LocationDetailsEntity
import com.molchanov.repository.data.locations.entityes.LocationPageEntity

/**
 * БД эпизодов
 */
@Database(
    entities = [
        LocationPageEntity::class,
        LocationDetailsEntity::class
    ], version = 1
)

abstract class LocationDB : RoomDatabase() {

    companion object {
        const val LOCATION_CHAR_DB_NAME = "LOCATION_CHARACTER_DB"
    }

    abstract fun getDAO(): LocationsDAO
}