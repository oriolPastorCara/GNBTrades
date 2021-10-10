package com.oriolpastor.gnbtrades.feature.splashScreen.domain

import com.oriolpastor.gnbtrades.base.domain.GenericErrors
import com.oriolpastor.gnbtrades.common.MyResult
import com.oriolpastor.gnbtrades.feature.splashScreen.data.models.RateResponse

interface TransactionsDataSource {
    suspend fun getRatesList(): MyResult<List<RateResponse>, GenericErrors>
    suspend fun getTransactionsList(): MyResult<Map<String, List<TransactionData>>, GenericErrors>
}