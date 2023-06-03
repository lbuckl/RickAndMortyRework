package com.molchanov.core.domain.repository.cases

/**
 * Интерфейс для получения основных данных с помощью RxJava
 */
interface IGetData<IN, OUT> {
    fun getData(requestData: IN): OUT
}