package com.molchanov.feature_episodes.mappers

import com.molchanov.core.utils.findWordInText
import com.molchanov.domain.episodes.Episode
import com.molchanov.domain.episodes.EpisodePage
import com.molchanov.feature_episodes.data.remote.dto.EpisodeDetails
import com.molchanov.feature_episodes.data.remote.dto.EpisodesDto
import javax.inject.Inject

class DtoDomainMapper @Inject constructor() {

    fun episodesDTOtoDomainPage(episodes: EpisodesDto, page: Int): EpisodePage =
        EpisodePage(
            pageNum = episodes.info.pages,
            pageActual = page,
            episodes.episodeDetails.map {
                episodeDTOtoDomain(it)
            }
        )

    fun episodeDTOtoDomain(episode: EpisodeDetails): Episode =
        Episode(
            id = episode.id,
            name = episode.name,
            airDate = episode.airDate,
            episode = episode.episode,
            characters = episode.characters.map {
                it
            },
            url = episode.url
        )

    fun episodesDTOtoDomainPageSearched(
        episode: EpisodesDto,
        page: Int,
        searchWord: String
    ): EpisodePage {

        val result = mutableListOf<EpisodeDetails>()

        episode.episodeDetails.forEach {
            if (
                findWordInText(it.name, searchWord) ||
                findWordInText(it.airDate, searchWord) ||
                findWordInText(it.episode, searchWord)
            ) {
                result.add(it)
            }
        }

        return EpisodePage(
            pageNum = episode.info.pages,
            pageActual = page,
            result.toList().map {
                episodeDTOtoDomain(it)
            }
        )
    }

}