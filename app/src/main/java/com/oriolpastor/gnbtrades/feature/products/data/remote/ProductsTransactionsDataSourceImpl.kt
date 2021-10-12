package com.oriolpastor.gnbtrades.feature.products.data.remote

import android.accounts.NetworkErrorException
import com.oriolpastor.gnbtrades.base.domain.GenericErrors
import com.oriolpastor.gnbtrades.common.MyResult
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.common.remote.ApiService
import com.oriolpastor.gnbtrades.feature.products.domain.remote.ProductsTransactionsDataSource

class ProductsTransactionsDataSourceImpl(private val apiService: ApiService) : ProductsTransactionsDataSource {
    override suspend fun getRatesList(): MyResult<List<Rate>, GenericErrors> =
        apiService.execute { getRates().mapToDomain() }

    override suspend fun getTransactionsList(): MyResult<List<Product>, GenericErrors> =
        apiService.execute { getTransactions().mapToDomain() }

    private suspend fun <T> ApiService.execute(body: suspend ApiService.() -> T):
        MyResult<T, GenericErrors> =
        try {
            MyResult.Success(body())
        } catch (e: NetworkErrorException) {
            MyResult.Error(GenericErrors.NETWORK_ERROR)
        } catch (e: Exception) {
            MyResult.Error(GenericErrors.GENERIC_ERROR)
        }
}
