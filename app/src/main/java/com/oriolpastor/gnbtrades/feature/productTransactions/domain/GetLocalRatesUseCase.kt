package com.oriolpastor.gnbtrades.feature.productTransactions.domain

import com.oriolpastor.gnbtrades.base.domain.GenericErrors
import com.oriolpastor.gnbtrades.base.domain.UseCase
import com.oriolpastor.gnbtrades.common.MyResult
import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.feature.products.domain.local.LocalRatesDataSource
import java.lang.Exception

class GetLocalRatesUseCase (
    private val localRatesDataSource: LocalRatesDataSource
) : UseCase<Unit, MyResult<List<Rate>, GenericErrors>> {
    override suspend fun invoke(params: Unit): MyResult<List<Rate>, GenericErrors> = try {
        val localRates = localRatesDataSource.getRatesList()
        MyResult.Success(localRates)
    } catch (e: Exception) {
        MyResult.Error(GenericErrors.GENERIC_ERROR)
    }
}