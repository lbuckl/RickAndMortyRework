package com.molchanov.feature_episodes.data.dto


import com.google.gson.annotations.SerializedName

data class EpisodesDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val episodeDetails: List<EpisodeDetails>
)