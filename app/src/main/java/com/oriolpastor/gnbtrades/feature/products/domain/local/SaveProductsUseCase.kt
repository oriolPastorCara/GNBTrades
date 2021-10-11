package com.oriolpastor.gnbtrades.feature.products.domain.local

import com.oriolpastor.gnbtrades.base.domain.UseCase
import com.oriolpastor.gnbtrades.common.local.entities.Product

class SaveProductsUseCase(
    private val localProductsTransactionsDataSourceLocal: LocalProductsTransactionsDataSource
) : UseCase<List<Product>, Unit> {
    override suspend fun invoke(params: List<Product>) =
        localProductsTransactionsDataSourceLocal.saveProductsList(params)
}