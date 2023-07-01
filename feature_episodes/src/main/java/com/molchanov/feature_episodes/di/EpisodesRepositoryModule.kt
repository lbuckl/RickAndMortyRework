package com.molchanov.feature_episodes.di

import com.molchanov.core.domain.repository.IRepository
import com.molchanov.core.domain.repository.requests.ILocalRequest
import com.molchanov.core.domain.repository.requests.IRemoteRequest
import com.molchanov.domain.episodes.EpisodePage
import com.molchanov.feature_episodes.data.EpisodeFilterData
import com.molchanov.feature_episodes.data.EpisodeRepoImpl
import com.molchanov.feature_episodes.data.local.EpisodeRepoLocalImpl
import com.molchanov.feature_episodes.data.remote.EpisodeRepoRemoteImpl
import com.molchanov.feature_episodes.ui.viewmodel.EpisodesAppState
import dagger.Binds
import dagger.Module

@Module
interface EpisodesRepositoryModule {

    @Binds
    fun getEpisodeRepoImpl(
        episodeRepoImpl: EpisodeRepoImpl
    ): IRepository<EpisodesAppState, EpisodeFilterData>

    @Binds
    fun getEpisodeRepoRemoteImpl(
        episodeRepoRemoteImpl: EpisodeRepoRemoteImpl
    ): IRemoteRequest<Int, String, EpisodeFilterData, EpisodePage>

    @Binds
    fun bindEpisodeRepoLocalImpl(episodeRepoLocalImpl: EpisodeRepoLocalImpl): ILocalRequest<Int, EpisodeFilterData, String, EpisodePage>

    @Binds
    fun getEpisodeFilterData(episodeFilterData: EpisodeFilterData): EpisodeFilterData

}