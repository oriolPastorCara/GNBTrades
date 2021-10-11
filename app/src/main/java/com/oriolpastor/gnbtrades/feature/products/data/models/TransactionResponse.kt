package com.oriolpastor.gnbtrades.feature.products.data.models

import com.squareup.moshi.Json

data class TransactionResponse(
    @Json(name= "sku")val sku: String,
    @Json(name= "amount")val amount:String,
    @Json(name= "currency")val currency: String,
)
