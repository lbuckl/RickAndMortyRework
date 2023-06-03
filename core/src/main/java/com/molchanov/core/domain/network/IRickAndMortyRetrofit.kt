package com.molchanov.core.domain.network

import retrofit2.Retrofit

interface IRickAndMortyRetrofit {
    fun getRickAndMortyRetrofit(): Retrofit
}