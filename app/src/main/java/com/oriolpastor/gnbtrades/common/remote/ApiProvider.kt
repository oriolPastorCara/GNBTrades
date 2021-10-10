package com.oriolpastor.gnbtrades.common.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiProvider : KoinComponent {
    private val config: ConfigurationProvider by inject()

    fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    fun provideRetrofit(
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(config.baseUrl)
        .addConverterFactory(moshiConverterFactory)
        .build()

    fun provideMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create(
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    )
}