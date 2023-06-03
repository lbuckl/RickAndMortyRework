package com.molchanov.core.domain.repository.cases

/**
 * Интерфейс для получения информации отфильтрованной по категориям
 */
interface IGetFilteredDataSuspend<IN, FIL, OUT> {
    suspend fun getFilteredData(requestData: IN, filter: FIL): OUT
}