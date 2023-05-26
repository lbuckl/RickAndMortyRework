package com.molchanov.core.di.network

import retrofit2.Retrofit

interface NetworkProvider {
    fun provideRetrofit(): Retrofit
}