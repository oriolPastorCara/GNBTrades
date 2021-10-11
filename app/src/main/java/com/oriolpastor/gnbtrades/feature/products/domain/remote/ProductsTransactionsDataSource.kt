package com.oriolpastor.gnbtrades.feature.products.domain.remote

import com.oriolpastor.gnbtrades.base.domain.GenericErrors
import com.oriolpastor.gnbtrades.common.MyResult
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.feature.products.data.models.RateResponse

interface ProductsTransactionsDataSource {
    suspend fun getRatesList(): MyResult<List<Rate>, GenericErrors>
    suspend fun getTransactionsList(): MyResult<List<Product>, GenericErrors>
}