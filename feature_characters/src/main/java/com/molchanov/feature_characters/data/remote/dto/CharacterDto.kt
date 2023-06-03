package com.molchanov.feature_characters.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<CharacterDetails>
)