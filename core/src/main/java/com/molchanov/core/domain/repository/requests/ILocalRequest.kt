package com.molchanov.core.domain.repository.requests

import com.molchanov.core.domain.repository.cases.IGetData
import com.molchanov.core.domain.repository.cases.IGetFilteredData
import com.molchanov.core.domain.repository.cases.IGetSearchedData
import com.molchanov.core.domain.repository.cases.ISaveData
import io.reactivex.rxjava3.core.Single

/**
 * Интерфейс для реализации взаимодействия с БД с помощью RxJava
 * @KEY - ключ для запроса в сеть
 * @ID - доп ключ для поиска
 * @FIL - данные для фильтрации
 * @DATA - получаемые данные
 */
interface ILocalRequest<KEY, FIL, ID, DATA : Any> :
    IGetData<KEY, Single<DATA>>,
    IGetSearchedData<KEY, ID, Single<DATA>>,
    IGetFilteredData<KEY, FIL, Single<DATA>>,
    ISaveData<DATA, KEY>
