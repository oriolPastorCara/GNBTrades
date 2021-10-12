package com.oriolpastor.gnbtrades.feature.products

import com.oriolpastor.gnbtrades.feature.products.data.local.LocalProductsTransactionsDataSourceImpl
import com.oriolpastor.gnbtrades.feature.products.data.local.LocalRatesDataSourceImpl
import com.oriolpastor.gnbtrades.feature.products.data.remote.ProductsTransactionsDataSourceImpl
import com.oriolpastor.gnbtrades.feature.products.domain.local.LocalProductsTransactionsDataSource
import com.oriolpastor.gnbtrades.feature.products.domain.local.LocalRatesDataSource
import com.oriolpastor.gnbtrades.feature.products.domain.local.SaveProductsUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.local.SaveRatesUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.remote.GetProductsTransactionsUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.remote.GetRatesUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.remote.ProductsTransactionsDataSource
import com.oriolpastor.gnbtrades.feature.products.ui.ProductsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsModule = module {
    viewModel {
        ProductsViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }
    factory { GetRatesUseCase(get()) }
    factory { GetProductsTransactionsUseCase(get()) }
    factory { SaveProductsUseCase(get()) }
    factory { SaveRatesUseCase(get()) }
    factory<ProductsTransactionsDataSource> { ProductsTransactionsDataSourceImpl(get()) }
    factory<LocalProductsTransactionsDataSource> { LocalProductsTransactionsDataSourceImpl(get()) }
    factory<LocalRatesDataSource> { LocalRatesDataSourceImpl(get()) }
}
