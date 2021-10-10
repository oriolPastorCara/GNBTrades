package com.oriolpastor.gnbtrades.feature.splashScreen.data

import com.oriolpastor.gnbtrades.feature.splashScreen.data.models.TransactionResponse
import com.oriolpastor.gnbtrades.feature.splashScreen.domain.TransactionData

fun List<TransactionResponse>.mapToDomain() =
    this.groupBy ({ it.sku }, { TransactionData(it.amount, it.currency) })

