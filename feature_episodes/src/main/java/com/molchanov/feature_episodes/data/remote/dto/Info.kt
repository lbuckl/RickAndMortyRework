package com.molchanov.feature_episodes.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("pages")
    val pages: Int,
    @Transient
    @SerializedName("next")
    val next: String
)