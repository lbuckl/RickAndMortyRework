package com.molchanov.feature_characters.ui

import com.molchanov.core.domain.repository.IRepository
import com.molchanov.coreui.utils.APP_STATE_DELAY
import com.molchanov.coreui.viewmodel.BaseViewModel
import com.molchanov.feature_characters.data.CharacterFilterData
import com.molchanov.feature_characters.domain.Character
import com.molchanov.feature_characters.domain.CharacterPage
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val repo: IRepository<CharactersAppState, CharacterFilterData>
) : BaseViewModel<CharactersAppState>() {

    private var lastPageActual = 1

    private var lastSuccessState: CharacterPage? = null

    fun getLastSuccessStateValue(): CharacterPage? = lastSuccessState

    private var disposable: Disposable = Disposable.empty()

    //Постраничный запрос
    override fun getData(page: Int) {
        liveData.postValue(CharactersAppState.Loading(true))

        lastPageActual = page

        disposable = repo.getData(page)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    endLoading(it)
                },
                {
                    endLoading(CharactersAppState.Error("No data in DataBase"))
                }
            )
    }

    //Запрос с поиском по слову
    override fun searchData(searchWord: String) {
        /*liveData.postValue(CharactersAppState.Loading(true))

        disposable = repo.getSearchedData(lastPageActual, searchWord)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    endLoading(it)
                },
                {
                    endLoading(CharactersAppState.Error("No data in DataBase"))
                }
            )*/
    }

    //Запрос с фильтрацией по признакам
    /*fun getFilteredData(data: CharacterFilterData) {
        liveData.postValue(CharactersAppState.Loading(true))

        disposable = repo.getFilteredData(data)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    endLoading(it)
                },
                {
                    endLoading(CharactersAppState.Error("No data in DataBase"))
                }
            )
    }*/

    //Костыль для того, чтобы одновременно посланные команды не перетерали друг друга
    private fun endLoading(state: CharactersAppState) {

        liveData.postValue(CharactersAppState.Loading(false))

        Thread.sleep(APP_STATE_DELAY)

        liveData.postValue(state)

        if (state is CharactersAppState.Success) lastSuccessState = state.data
    }

    //Перезгрузка данных
    override fun reloadData() {
        getData(lastPageActual)
    }

    //Получить последний удачный запрос
    fun getLastSuccess() {
        lastSuccessState?.let { endLoading(CharactersAppState.Success(lastSuccessState!!)) }
    }

    //Выдать подробную инфомарцию о персонаже
    fun getDetailsInfo(character: Character) {
        liveData.postValue(CharactersAppState.SuccessCharacter(character))
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}