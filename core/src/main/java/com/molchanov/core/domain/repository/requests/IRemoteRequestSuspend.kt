package com.molchanov.core.domain.repository.requests

import com.molchanov.core.domain.repository.cases.IGetDataSuspend
import com.molchanov.core.domain.repository.cases.IGetFilteredDataSuspend
import com.molchanov.core.domain.repository.cases.IGetSearchedDataSuspend

/**
 * Интерфейс для реализации взаимодействия с API с помощью Coroutines
 * @KEY - ключ для запроса в сеть
 * @ID - доп ключ для поиска
 * @FIL - данные для фильтрации
 * @DATA - получаемые данные
 * @D - данные по детальной информации
 */
interface IRemoteRequestSuspend<KEY, ID, FIL, DATA : Any> :

    IGetDataSuspend<KEY, DATA>,
    IGetSearchedDataSuspend<KEY, ID, DATA>,
    IGetFilteredDataSuspend<KEY, FIL, DATA>