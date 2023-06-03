package com.molchanov.core.domain.repository.cases

/**
 * Интерфейс для сохранения данных в БД
 */
interface ISaveDataSuspend<IN, KEY> {
    suspend fun saveData(data: IN, key: KEY)
}