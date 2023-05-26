package com.molchanov.core.data.network

import com.molchanov.core.domain.IRickAndMortyRetrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject

/**
 * Основной класс для создания Retrofit объектов запроса в API Rick and Morty
 */
class RickAndMortyRetrofitImpl @Inject constructor(): IRickAndMortyRetrofit {

    private val baseUrl = "https://rickandmortyapi.com"

    override fun getRickAndMortyRetrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(createOkHttpClient(RMInterceptor()))
            .build()
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder().let {
            it.addInterceptor(interceptor)
            it.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return httpClient.build()
    }

    //Перехватчик для отлавливания колбэков о результате загрузки
    class RMInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }
}