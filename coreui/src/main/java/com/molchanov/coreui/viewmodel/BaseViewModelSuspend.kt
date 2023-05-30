package com.molchanov.coreui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Базовый класс для ViewModel рабочающих с помощью корутин
 */
abstract class BaseViewModelSuspend<V : AppState>(
    val liveData: MutableLiveData<V> = MutableLiveData<V>()
) : ViewModel() {

    private val outLiveData: LiveData<V> by lazy {
        liveData
    }

    abstract suspend fun getData(page: Int)

    abstract suspend fun reloadData()

    abstract suspend fun searchData(searchWord: String)

    suspend fun getMyLiveData(): LiveData<V> {

        if (liveData.value == null) getData(1)

        return outLiveData
    }


}