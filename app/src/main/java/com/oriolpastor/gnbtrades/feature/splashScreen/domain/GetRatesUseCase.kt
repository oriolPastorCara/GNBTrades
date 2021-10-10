package com.oriolpastor.gnbtrades.feature.splashScreen.domain

import com.oriolpastor.gnbtrades.base.domain.GenericErrors
import com.oriolpastor.gnbtrades.base.domain.UseCase
import com.oriolpastor.gnbtrades.common.MyResult
import com.oriolpastor.gnbtrades.feature.splashScreen.data.models.RateResponse

class GetRatesUseCase(private val transactionsDataSource: TransactionsDataSource) :
    UseCase<Unit, MyResult<List<RateResponse>, GenericErrors>> {
    override suspend fun invoke(params: Unit): MyResult<List<RateResponse>, GenericErrors> =
        transactionsDataSource.getRatesList()
}