package com.molchanov.feature_episodes.data

import com.molchanov.core.domain.filter.BaseFilterData

/**
 * Класс для фильтров эпизодов
 */
data class EpisodeFilterData(
    val name: String?,
    val episode: String?,
) : BaseFilterData()

fun EpisodeFilterData.getRoomFormat(): EpisodeFilterData {
    return EpisodeFilterData(
        if (this.name.isNullOrBlank()) "%%" else "%${this.name}%",
        if (this.episode.isNullOrBlank()) "%%" else "%${this.episode}%"
    )
}