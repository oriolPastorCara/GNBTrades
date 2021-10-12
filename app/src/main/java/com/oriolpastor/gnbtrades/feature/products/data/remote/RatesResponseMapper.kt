package com.oriolpastor.gnbtrades.feature.products.data.remote

import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.feature.products.data.models.RateResponse

fun List<RateResponse>.mapToDomain() = this.map {
    Rate(from = it.from, to = it.to, rate = it.rate.toFloat())
}
