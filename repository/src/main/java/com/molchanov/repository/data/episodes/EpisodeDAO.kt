package com.molchanov.repository.data.episodes

import androidx.room.*
import com.molchanov.repository.data.episodes.entityes.EpisodeDetailsEntity
import com.molchanov.repository.data.episodes.entityes.EpisodePageAndDetails
import com.molchanov.repository.data.episodes.entityes.EpisodePageEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface EpisodeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEpisode(episode: EpisodePageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEpisodesDetails(characters: List<EpisodeDetailsEntity>)


    @Query("SELECT * FROM EpisodePageTab WHERE url = :reqUrl")
    fun queryEpisodeByUrl(reqUrl: String): Single<EpisodePageEntity>

    @Query("SELECT * FROM EpisodePageTab WHERE pageActual = :charPage")
    fun queryPageAndCharacters(charPage: Int): Single<List<EpisodePageAndDetails>>

    @Query(
        "SELECT * FROM EpisodePageTab WHERE name LIKE :reqName AND episode LIKE :reqEpisode "
    )
    fun queryFilteredPageAndCharacters(
        reqName: String,
        reqEpisode: String
    ): Single<List<EpisodePageAndDetails>>
}