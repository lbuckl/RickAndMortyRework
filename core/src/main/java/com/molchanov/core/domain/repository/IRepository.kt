package com.molchanov.core.domain.repository

import com.molchanov.core.domain.filter.BaseFilterData
import com.molchanov.core.domain.viewmodel.AppState
import io.reactivex.rxjava3.core.Single

interface IRepository<AS : AppState, FIL : BaseFilterData> {

    fun getData(page: Int): Single<AS>

    fun getSearchedData(page: Int, searchWord: String): Single<AS>

    fun getFilteredData(filter: FIL): Single<AS>
}