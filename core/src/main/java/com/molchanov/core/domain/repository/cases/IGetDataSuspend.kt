package com.molchanov.core.domain.repository.cases

/**
 * Интерфейс для получения основных данных
 */
interface IGetDataSuspend<IN, OUT> {
    suspend fun getData(requestData: IN): OUT
}