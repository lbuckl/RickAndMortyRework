package com.molchanov.feature_characters.data.local

import androidx.room.*
import com.molchanov.feature_characters.data.local.entity.CharacterDetailsEntity
import com.molchanov.feature_characters.data.local.entity.CharacterPageAndDetails
import com.molchanov.feature_characters.data.local.entity.CharacterPageEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface CharactersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterPageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterDetails(episodes: List<CharacterDetailsEntity>)

    @Query("SELECT * FROM CharacterPageTab WHERE url = :reqUrl")
    fun queryCharacterByUrl(reqUrl: String): Single<CharacterPageEntity>

    @Query("SELECT * FROM CharacterPageTab WHERE pageActual = :pageAct")
    fun queryPageAndEpisodes(pageAct: Int): Single<List<CharacterPageAndDetails>>

    @Query("SELECT * FROM CharacterPageTab WHERE name LIKE :reqName AND status LIKE :reqStatus " +
                "AND spec LIKE :reqSpecies AND gender LIKE :reqGender")
    fun queryFilteredPageAndEpisodes(
        reqName: String,
        reqStatus: String,
        reqSpecies: String,
        reqGender: String
    ): Single<List<CharacterPageAndDetails>>
}