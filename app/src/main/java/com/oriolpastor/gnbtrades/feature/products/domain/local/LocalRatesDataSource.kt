package com.oriolpastor.gnbtrades.feature.products.domain.local

import com.oriolpastor.gnbtrades.common.local.entities.Rate

interface LocalRatesDataSource {
    suspend fun saveRatesList(data: List<Rate>)
    suspend fun getRatesList(): List<Rate>
}