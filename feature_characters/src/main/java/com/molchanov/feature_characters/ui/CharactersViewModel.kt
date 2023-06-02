package com.molchanov.feature_characters.ui

import com.molchanov.coreui.utils.APP_STATE_DELAY
import com.molchanov.coreui.viewmodel.BaseViewModel
import com.molchanov.feature_characters.domain.CharacterPage
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject
import com.molchanov.feature_characters.domain.Character

/**
 * ViewModel реализующая бизнес-логику:
 * - обращение к API и БД
 * - определение состояний для CharactersFragment
 */
class CharactersViewModel @Inject constructor() : BaseViewModel<CharactersAppState>() {

    //Хранение последней запрашиваемой страницы
    private var lastPageActual = 1

    //Хранение последнего удачного запроса
    private var lastSuccessState: CharacterPage? = null

    fun getLastSuccessStateValue(): CharacterPage? = lastSuccessState

    //region инициализация переменных
    /*@Inject
    @CharacterRepository
    lateinit var repo: IRepository<CharactersAppState, CharacterFilterData>*/

    private var disposable: Disposable = Disposable.empty()

    //Постраничный запрос
    override fun getData(page: Int) {
        liveData.postValue(CharactersAppState.Loading(true))

        lastPageActual = page

        /*disposable = repo.getData(page)
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