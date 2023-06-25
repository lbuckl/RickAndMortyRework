package com.molchanov.repository.data.locations

import androidx.room.*
import com.molchanov.repository.data.locations.entityes.LocationDetailsEntity
import com.molchanov.repository.data.locations.entityes.LocationPageAndDetails
import com.molchanov.repository.data.locations.entityes.LocationPageEntity

@Dao
interface LocationsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: LocationPageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocationDetails(characters: List<LocationDetailsEntity>)


    @Query("SELECT * FROM LocationPageTab WHERE pageActual = :pageAct")
    fun queryLocationAndDetails(pageAct: Int): List<LocationPageAndDetails>

    @Query(
        "SELECT * FROM LocationPageTab WHERE name LIKE :reqName " +
                "AND type LIKE :reqType AND dimension LIKE :reqDimension"
    )
    fun queryFilteredLocationPageAndDetails(
        reqName: String,
        reqType: String,
        reqDimension: String
    ): List<LocationPageAndDetails>
}