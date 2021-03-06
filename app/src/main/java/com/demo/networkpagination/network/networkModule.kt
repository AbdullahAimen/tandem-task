package com.demo.networkpagination.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val networkModule = module {
    /**
     * provide Retrofit Service
     * */
    single<ApiHelper> {
        provideRetrofitService(get())
    }
    single<Retrofit> {
        val logger =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Timber.d("API: $it") })
        logger.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        Retrofit.Builder()
            .baseUrl(ApiHelper.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

fun provideRetrofitService(retrofit: Retrofit): ApiHelper =
    retrofit.create(ApiHelper::class.java)
