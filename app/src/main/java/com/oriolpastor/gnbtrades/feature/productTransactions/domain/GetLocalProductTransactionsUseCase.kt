package com.oriolpastor.gnbtrades.feature.productTransactions.domain

import com.oriolpastor.gnbtrades.base.domain.GenericErrors
import com.oriolpastor.gnbtrades.base.domain.UseCase
import com.oriolpastor.gnbtrades.common.MyResult
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.feature.products.domain.local.LocalProductsTransactionsDataSource
import com.oriolpastor.gnbtrades.feature.products.domain.local.LocalRatesDataSource
import java.lang.Exception

class GetLocalProductTransactionsUseCase (
    private val localProductTransactionsDataSource: LocalProductsTransactionsDataSource
) : UseCase<String, MyResult<Product, GenericErrors>> {
    override suspend fun invoke(sku: String) = try {
        val localRates = localProductTransactionsDataSource.getAllProductsBySku(sku)
        MyResult.Success(localRates)
    } catch (e: Exception) {
        MyResult.Error(GenericErrors.GENERIC_ERROR)
    }
}

