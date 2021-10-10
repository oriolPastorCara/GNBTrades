package com.oriolpastor.gnbtrades.feature.splashScreen.data

import android.accounts.NetworkErrorException
import com.oriolpastor.gnbtrades.base.domain.GenericErrors
import com.oriolpastor.gnbtrades.common.MyResult
import com.oriolpastor.gnbtrades.common.remote.ApiService
import com.oriolpastor.gnbtrades.feature.splashScreen.data.models.RateResponse
import com.oriolpastor.gnbtrades.feature.splashScreen.domain.TransactionData
import com.oriolpastor.gnbtrades.feature.splashScreen.domain.TransactionsDataSource
import java.lang.Exception

class TransactionsDataSourceImpl(private val apiService: ApiService) : TransactionsDataSource {
    override suspend fun getRatesList(): MyResult<List<RateResponse>, GenericErrors> =
        apiService.execute { getRates() }

    override suspend fun getTransactionsList():
            MyResult<Map<String, List<TransactionData>>, GenericErrors> =
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