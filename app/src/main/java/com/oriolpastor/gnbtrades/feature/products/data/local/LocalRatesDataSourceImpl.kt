package com.oriolpastor.gnbtrades.feature.products.data.local

import com.oriolpastor.gnbtrades.common.local.AppDatabase
import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.feature.products.domain.local.LocalRatesDataSource

class LocalRatesDataSourceImpl(
    private val room: AppDatabase
) : LocalRatesDataSource{
    override suspend fun saveRatesList(data: List<Rate>) =
        room.ratesDao().insertAll(data)

    override suspend fun getRatesList(): List<Rate> =
        room.ratesDao().getRates()
}