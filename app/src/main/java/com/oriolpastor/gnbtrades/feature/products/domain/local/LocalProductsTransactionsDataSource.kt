package com.oriolpastor.gnbtrades.feature.products.domain.local

import com.oriolpastor.gnbtrades.common.local.entities.Product

interface LocalProductsTransactionsDataSource {
    suspend fun saveProductsList(data: List<Product>)
    suspend fun getProductsList(): List<String>
    suspend fun getAllProductsBySku(sku: String): Product
}
