package com.oriolpastor.gnbtrades.feature.products.data.remote

import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.feature.products.data.models.TransactionResponse
import com.oriolpastor.gnbtrades.feature.products.domain.remote.models.TransactionData

fun List<TransactionResponse>.mapToDomain() =
    this.groupBy({ it.sku }, { TransactionData(it.amount, it.currency) }).mapToDomain()

fun Map<String, List<TransactionData>>.mapToDomain(): List<Product> {
    val dataList = mutableListOf<Product>()
    this.map { transactions ->
        dataList.add(Product(transactions.key, transactions.value))
    }
    return dataList.toList()
}
