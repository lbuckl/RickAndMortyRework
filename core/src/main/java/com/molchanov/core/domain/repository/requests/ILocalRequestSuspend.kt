package com.molchanov.core.domain.repository.requests

import com.molchanov.core.domain.repository.cases.IGetDataSuspend
import com.molchanov.core.domain.repository.cases.IGetFilteredDataSuspend
import com.molchanov.core.domain.repository.cases.IGetSearchedDataSuspend
import com.molchanov.core.domain.repository.cases.ISaveDataSuspend

/**
 * Интерфейс для реализации взаимодействия с БД с помощью Coroutines
 * @KEY - ключ для запроса в сеть
 * @ID - доп ключ для поиска
 * @FIL - данные для фильтрации
 * @DATA - получаемые данные
 */
interface ILocalRequestSuspend<KEY, FIL, ID, DATA : Any> :
    IGetDataSuspend<KEY, DATA>,
    IGetSearchedDataSuspend<KEY, ID, DATA>,
    IGetFilteredDataSuspend<KEY, FIL, DATA>,
    ISaveDataSuspend<DATA, KEY>
