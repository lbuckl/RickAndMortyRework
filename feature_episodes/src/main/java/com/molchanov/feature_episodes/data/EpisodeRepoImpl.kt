package com.molchanov.feature_episodes.data

import com.molchanov.core.domain.network.INetworkStatus
import com.molchanov.core.domain.repository.IRepository
import com.molchanov.core.domain.repository.requests.ILocalRequest
import com.molchanov.core.domain.repository.requests.IRemoteRequest
import com.molchanov.domain.episodes.EpisodePage
import com.molchanov.feature_episodes.ui.viewmodel.EpisodesAppState
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class EpisodeRepoImpl @Inject constructor(
    private val repoRemote: IRemoteRequest<Int, String, EpisodeFilterData, EpisodePage>,
    private val repoLocal: ILocalRequest<Int, EpisodeFilterData, String, EpisodePage>,
    networkStatus: INetworkStatus
) : IRepository<EpisodesAppState, EpisodeFilterData> {

    private var networkStatusResult: Boolean? = null

    //Хранение последней запрашиваемой страницы
    private var lastPageActual = 1

    init {
        networkStatus.isOnline().subscribe(
            {
                networkStatusResult = it
            },
            {
                networkStatusResult = null
            }
        )
    }

    override fun getData(page: Int): Single<EpisodesAppState> {

        lastPageActual = page

        return when (networkStatusResult) {
            true -> {
                repoRemote.getData(page)
                    .subscribeOn(Schedulers.io())
                    .flatMap {
                        repoLocal.saveData(it, page)
                        Single.create<EpisodesAppState> { emmiter ->
                            emmiter.onSuccess(EpisodesAppState.Success(it))
                        }
                    }
                    .doOnError {
                        reserveGetRequest(page)
                    }
            }
            else -> reserveGetRequest(page)
        }
    }

    //Резервный постраничный запрос в БД
    private fun reserveGetRequest(page: Int): Single<EpisodesAppState> {
        return repoLocal.getData(page)
            .subscribeOn(Schedulers.io())
            .flatMap {
                Single.create { emmiter ->
                    emmiter.onSuccess(EpisodesAppState.Success(it))
                }
            }
    }

    override fun getSearchedData(page: Int, searchWord: String): Single<EpisodesAppState> {

        return when (networkStatusResult) {
            true -> {

                repoRemote.getSearchedData(lastPageActual, searchWord)
                    .subscribeOn(Schedulers.io())
                    .flatMap {
                        Single.create<EpisodesAppState> { emmiter ->
                            emmiter.onSuccess(EpisodesAppState.Success(it))
                        }
                    }
                    .doOnError {
                        reserveSearchRequest(searchWord)
                    }
            }
            else -> reserveSearchRequest(searchWord)
        }
    }

    //Резервный запрос в БД с поиском по слову
    private fun reserveSearchRequest(searchWord: String): Single<EpisodesAppState> {

        return repoLocal.getSearchedData(lastPageActual, searchWord)
            .subscribeOn(Schedulers.io())
            .flatMap {
                Single.create { emmiter ->
                    emmiter.onSuccess(EpisodesAppState.Success(it))
                }
            }
    }

    override fun getFilteredData(filter: EpisodeFilterData): Single<EpisodesAppState> {

        return when (networkStatusResult) {
            true -> {

                repoRemote.getFilteredData(lastPageActual, filter)
                    .subscribeOn(Schedulers.io())
                    .flatMap {
                        Single.create<EpisodesAppState> { emmiter ->
                            emmiter.onSuccess(EpisodesAppState.Success(it))
                        }
                    }
                    .doOnError {
                        reserveFilteredRequest(filter)
                    }
            }
            else -> {
                reserveFilteredRequest(filter)
            }
        }
    }

    //Резервный запрос в БД с фильтрацией по признакам
    private fun reserveFilteredRequest(data: EpisodeFilterData): Single<EpisodesAppState> {
        return repoLocal.getFilteredData(lastPageActual, data)
            .subscribeOn(Schedulers.io())
            .flatMap {
                Single.create { emmiter ->
                    emmiter.onSuccess(EpisodesAppState.Success(it))
                }
            }
    }
}