package com.molchanov.core.domain.repository.cases

/**
 * Интерфейс для сохранения данных в БД с помощью RxJava
 */
interface ISaveData<IN, KEY> {
    fun saveData(data: IN, key: KEY)
}