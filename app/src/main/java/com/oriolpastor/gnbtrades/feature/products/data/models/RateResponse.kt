package com.oriolpastor.gnbtrades.feature.products.data.models

import com.squareup.moshi.Json

data class RateResponse(
    @Json(name = "from")val from: String,
    @Json(name = "to")val to: String,
    @Json(name = "rate")val rate: String,
)
