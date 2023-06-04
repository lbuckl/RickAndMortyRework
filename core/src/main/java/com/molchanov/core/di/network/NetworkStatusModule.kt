package com.molchanov.core.di.network

import com.molchanov.core.data.network.NetworkStatus
import com.molchanov.core.domain.network.INetworkStatus
import dagger.Binds
import dagger.Module

@Module
interface NetworkStatusModule {

    @Binds
    fun bindNetworkStatus(status: NetworkStatus): INetworkStatus
}