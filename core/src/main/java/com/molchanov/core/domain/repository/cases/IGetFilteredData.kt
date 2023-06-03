package com.molchanov.core.domain.repository.cases

/**
 * Интерфейс для получения информации отфильтрованной по категориям с помощью RxJava
 */
interface IGetFilteredData<IN, FIL, OUT> {
    fun getFilteredData(requestData: IN, filter: FIL): OUT
}