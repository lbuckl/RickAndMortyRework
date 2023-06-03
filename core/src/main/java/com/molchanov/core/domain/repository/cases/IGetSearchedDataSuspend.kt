package com.molchanov.core.domain.repository.cases

/**
 * Интерфейс для получения данных найденных по ключевому слову
 */
interface IGetSearchedDataSuspend<IN, KEY, OUT> {
    suspend fun getSearchedData(requestData: IN, searchWord: KEY): OUT
}