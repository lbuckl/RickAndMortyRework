package com.molchanov.core.domain.repository.requests

import com.molchanov.core.domain.repository.cases.IGetData
import com.molchanov.core.domain.repository.cases.IGetFilteredData
import com.molchanov.core.domain.repository.cases.IGetSearchedData
import io.reactivex.rxjava3.core.Single

/**
 * Интерфейс для реализации взаимодействия с API с помощью RxJava
 * @KEY - ключ для запроса в сеть
 * @ID - доп ключ для поиска
 * @FIL - данные для фильтрации
 * @DATA - получаемые данные
 * @D - данные по детальной информации
 */
interface IRemoteRequest<KEY, ID, FIL, DATA : Any> :

    IGetData<KEY, Single<DATA>>,
    IGetSearchedData<KEY, ID, Single<DATA>>,
    IGetFilteredData<KEY, FIL, Single<DATA>>