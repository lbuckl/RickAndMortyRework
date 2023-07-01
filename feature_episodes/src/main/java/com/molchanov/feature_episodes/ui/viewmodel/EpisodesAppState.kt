package com.molchanov.feature_episodes.ui.viewmodel

import com.molchanov.core.domain.viewmodel.AppState
import com.molchanov.domain.episodes.Episode
import com.molchanov.domain.episodes.EpisodePage

sealed class EpisodesAppState : AppState() {

    //Состояния страницы
    data class Loading(val isLoading: Boolean) : EpisodesAppState()
    data class Success(val data: EpisodePage) : EpisodesAppState()
    data class Error(val errorMsg: String) : EpisodesAppState()

    //Состояния детальной информации
    data class SuccessEpisode(val data: Episode) : EpisodesAppState()

}