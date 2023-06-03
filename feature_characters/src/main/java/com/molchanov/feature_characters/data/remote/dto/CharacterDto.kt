package com.molchanov.feature_characters.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.molchanov.feature_characters.data.remote.dto.CharacterDetails
import com.molchanov.feature_characters.data.remote.dto.Info

data class CharacterDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<CharacterDetails>
)