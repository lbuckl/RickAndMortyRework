package com.molchanov.core.domain

import retrofit2.Retrofit

interface IRickAndMortyRetrofit {
    fun getRickAndMortyRetrofitBuilder(): Retrofit
}