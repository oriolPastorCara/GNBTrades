package com.oriolpastor.gnbtrades.common.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiProvider : KoinComponent {

    fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    fun provideRetrofit(
        moshiConverterFactory: MoshiConverterFactory,
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://quiet-stone-2094.herokuapp.com/")
        .addConverterFactory(moshiConverterFactory)
        .client(client)
        .build()

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(RequestInterceptor())
            .build()
    }

    fun provideMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create(
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    )
}
