package com.molchanov.core.di.network

import com.molchanov.core.domain.network.INetworkStatus
import com.molchanov.core.domain.network.IRickAndMortyRetrofit

interface NetworkProvider {
    fun provideRetrofit(): IRickAndMortyRetrofit

    //fun provideNetworkStatus(): INetworkStatus
}