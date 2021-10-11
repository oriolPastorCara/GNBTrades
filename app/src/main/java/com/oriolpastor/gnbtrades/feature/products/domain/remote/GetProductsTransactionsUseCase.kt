package com.oriolpastor.gnbtrades.feature.products.domain.remote

import com.oriolpastor.gnbtrades.base.domain.GenericErrors
import com.oriolpastor.gnbtrades.base.domain.UseCase
import com.oriolpastor.gnbtrades.common.MyResult
import com.oriolpastor.gnbtrades.common.local.entities.Product

class GetProductsTransactionsUseCase(private val productsTransactionsDataSource: ProductsTransactionsDataSource) :
    UseCase<Unit, MyResult<List<Product>, GenericErrors>> {
    override suspend fun invoke(params: Unit):
            MyResult<List<Product>, GenericErrors> = productsTransactionsDataSource.getTransactionsList()
}
