package com.molchanov.feature_characters.data

import com.molchanov.core.domain.network.INetworkStatus
import com.molchanov.core.domain.repository.IRepository
import com.molchanov.core.domain.repository.requests.ILocalRequest
import com.molchanov.core.domain.repository.requests.IRemoteRequest
import com.molchanov.feature_characters.domain.CharacterPage
import com.molchanov.feature_characters.ui.CharactersAppState
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CharactersRepoImpl @Inject constructor(
    private val repoRemote: IRemoteRequest<Int, String, CharacterFilterData, CharacterPage>,
    private val repoLocal: ILocalRequest<Int, CharacterFilterData, String, CharacterPage>,
    networkStatus: INetworkStatus
) : IRepository<CharactersAppState, CharacterFilterData> {

    private var networkStatusResult: Boolean? = null

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

    override fun getData(page: Int): Single<CharactersAppState> {

        lastPageActual = page

        return when (networkStatusResult) {
            true -> {
                return repoRemote.getData(page)
                    .subscribeOn(Schedulers.io())
                    .flatMap {
                        repoLocal.saveData(it, page)

                        Single.create<CharactersAppState> { emmiter ->
                            emmiter.onSuccess(CharactersAppState.Success(it))
                        }
                    }
                    .doOnError {
                        reserveGetRequest(page)
                    }
            }
            else -> reserveGetRequest(page)
        }
    }

    private fun reserveGetRequest(page: Int): Single<CharactersAppState> {
        return repoLocal.getData(page)
            .subscribeOn(Schedulers.io())
            .flatMap {
                Single.create { emmiter ->
                    emmiter.onSuccess(CharactersAppState.Success(it))
                }
            }
    }

    override fun getSearchedData(page: Int, searchWord: String): Single<CharactersAppState> {

        return when (networkStatusResult) {
            true -> {

                return repoRemote.getSearchedData(lastPageActual, searchWord)
                    .subscribeOn(Schedulers.io())
                    .flatMap {
                        Single.create<CharactersAppState> { emmiter ->
                            emmiter.onSuccess(CharactersAppState.Success(it))
                        }
                    }
                    .doOnError {
                        //reserveSearchRequest(searchWord)
                    }
            }
            else -> reserveSearchRequest(searchWord)
        }
    }

    //Резервный запрос в БД с поиском по слову
    private fun reserveSearchRequest(searchWord: String): Single<CharactersAppState> {

        return repoLocal.getSearchedData(lastPageActual, searchWord)
            .subscribeOn(Schedulers.io())
            .flatMap {
                Single.create { emmiter ->
                    emmiter.onSuccess(CharactersAppState.Success(it))
                }
            }
    }

    override fun getFilteredData(filter: CharacterFilterData): Single<CharactersAppState> {

        return when (networkStatusResult) {
            true -> {
                return repoRemote.getFilteredData(lastPageActual, filter)
                    .subscribeOn(Schedulers.io())
                    .flatMap {
                        Single.create<CharactersAppState> { emmiter ->
                            emmiter.onSuccess(CharactersAppState.Success(it))
                        }
                    }
                    .doOnError {
                        //reserveFilteredRequest(filter)
                    }
            }
            else -> {
                reserveFilteredRequest(filter)
            }
        }
    }

    //Резервный запрос в БД с фильтрацией по признакам
    private fun reserveFilteredRequest(data: CharacterFilterData): Single<CharactersAppState> {
        return repoLocal.getFilteredData(lastPageActual, data)
            .subscribeOn(Schedulers.io())
            .flatMap {
                Single.create { emmiter ->
                    emmiter.onSuccess(CharactersAppState.Success(it))
                }
            }
    }
}