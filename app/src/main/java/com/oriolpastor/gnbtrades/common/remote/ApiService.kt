package com.oriolpastor.gnbtrades.common.remote

import com.oriolpastor.gnbtrades.feature.splashScreen.data.models.RateResponse
import com.oriolpastor.gnbtrades.feature.splashScreen.data.models.TransactionResponse
import retrofit2.http.GET

interface ApiService {
        @GET("/rates.json")
        suspend fun getRates(): List<RateResponse>

        @GET("/transactions.json")
        suspend fun getTransactions(): List<TransactionResponse>
}