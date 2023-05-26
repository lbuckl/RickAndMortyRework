package com.molchanov.core.di.network

import com.molchanov.core.data.network.NetworkStatus
import com.molchanov.core.data.network.RickAndMortyRetrofitImpl
import com.molchanov.core.domain.INetworkStatus
import com.molchanov.core.domain.IRickAndMortyRetrofit
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {

    @Binds
    fun bindRetrofit(retrofit: RickAndMortyRetrofitImpl): IRickAndMortyRetrofit

    @Binds
    fun bindNetworkStatus(networkStatus: NetworkStatus): INetworkStatus
}