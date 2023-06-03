package com.molchanov.core.domain.repository.cases

/**
 * Интерфейс для получения данных найденных по ключевому слову с помощью RxJava
 */
interface IGetSearchedData<IN, KEY, OUT> {
    fun getSearchedData(requestData: IN, searchWord: KEY): OUT
}