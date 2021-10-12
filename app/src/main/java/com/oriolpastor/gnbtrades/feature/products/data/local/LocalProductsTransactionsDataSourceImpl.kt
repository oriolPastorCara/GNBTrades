package com.oriolpastor.gnbtrades.feature.products.data.local

import com.oriolpastor.gnbtrades.common.local.AppDatabase
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.feature.products.domain.local.LocalProductsTransactionsDataSource

class LocalProductsTransactionsDataSourceImpl(
    private val room: AppDatabase
) : LocalProductsTransactionsDataSource {
    override suspend fun saveProductsList(data: List<Product>) = room.productsDao().replaceAll(data)

    override suspend fun getProductsList(): List<String> = room.productsDao().getAllProductsIds()

    override suspend fun getAllProductsBySku(sku: String): Product =
        room.productsDao().getAllProductsBySku(sku)
}
