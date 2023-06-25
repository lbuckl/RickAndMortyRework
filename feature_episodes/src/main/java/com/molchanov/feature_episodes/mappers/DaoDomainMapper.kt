package com.molchanov.feature_episodes.mappers


import com.molchanov.core.utils.findWordInText
import com.molchanov.domain.episodes.*
import com.molchanov.repository.data.episodes.entityes.EpisodeDetailsEntity
import com.molchanov.repository.data.episodes.entityes.EpisodePageAndDetails
import com.molchanov.repository.data.episodes.entityes.EpisodePageEntity
import javax.inject.Inject

class DaoDomainMapper @Inject constructor() {

    fun episodeDomainToDao(episode: Episode, pageActual: Int, pages: Int): EpisodePageEntity =
        EpisodePageEntity(
            episode.id.toLong(),
            pages,
            pageActual,
            episode.name,
            episode.airDate,
            episode.episode,
            episode.url
        )

    fun episodeDetailsDomainToDao(episode: Episode): List<EpisodeDetailsEntity> {

        val result: MutableList<EpisodeDetailsEntity> = mutableListOf()

        episode.characters.forEach {
            result.add(
                EpisodeDetailsEntity(
                    episode.id.toLong(),
                    it
                )
            )
        }

        return result.toList()
    }

    fun daoEpisodesAndCharactersToDomain(
        episodePageAndCharacters: List<EpisodePageAndDetails>
    ): EpisodePage = EpisodePage(
        episodePageAndCharacters[0].episodePage.pageNum,
        episodePageAndCharacters[0].episodePage.pageActual,
        episodePageAndCharacters.map {
            Episode(
                it.episodePage.id.toInt(),
                it.episodePage.name,
                it.episodePage.airDate,
                it.episodePage.episode,
                it.episodeCharacterEntities.map { ep ->
                    ep.character
                },
                it.episodePage.url
            )
        }
    )

    fun daoEpisodesAndCharactersToDomainSearch(
        episodePageAndCharacters: List<EpisodePageAndDetails>,
        searchWord: String
    ): EpisodePage {

        val result = mutableListOf<EpisodePageAndDetails>()

        episodePageAndCharacters.forEach {
            if (findWordInText(it.episodePage.name, searchWord) ||
                findWordInText(it.episodePage.episode, searchWord)
            ) result.add(it)
        }

        return EpisodePage(
            episodePageAndCharacters[0].episodePage.pageNum,
            episodePageAndCharacters[0].episodePage.pageActual,
            result.toList().map {
                Episode(
                    it.episodePage.id.toInt(),
                    it.episodePage.name,
                    it.episodePage.airDate,
                    it.episodePage.episode,
                    it.episodeCharacterEntities.map { ep ->
                        ep.character
                    },
                    it.episodePage.url
                )
            }
        )
    }

    fun daoEpisodeToDomainDetail(episode: EpisodePageEntity): Episode {
        return Episode(
            episode.id.toInt(),
            episode.name,
            episode.airDate,
            episode.episode,
            listOf(),
            episode.url
        )
    }
}