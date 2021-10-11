package com.oriolpastor.gnbtrades.feature.products.domain.local

import com.oriolpastor.gnbtrades.base.domain.UseCase
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.local.entities.Rate

class SaveRatesUseCase(
    private val localRatesDataSourceLocal: LocalRatesDataSource
) : UseCase<List<Rate>, Unit> {
    override suspend fun invoke(params: List<Rate>) =
        localRatesDataSourceLocal.saveRatesList(params)
}