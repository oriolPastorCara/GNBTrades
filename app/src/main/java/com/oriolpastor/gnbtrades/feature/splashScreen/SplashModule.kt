package com.oriolpastor.gnbtrades.feature.splashScreen

import com.oriolpastor.gnbtrades.feature.splashScreen.data.TransactionsDataSourceImpl
import com.oriolpastor.gnbtrades.feature.splashScreen.domain.GetRatesUseCase
import com.oriolpastor.gnbtrades.feature.splashScreen.domain.GetTransactionsUseCase
import com.oriolpastor.gnbtrades.feature.splashScreen.domain.TransactionsDataSource
import com.oriolpastor.gnbtrades.feature.products.ui.ProductsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel {
        ProductsViewModel(
            get(),
            get(),
            get(),
        )
    }
    factory { GetRatesUseCase(get()) }
    factory { GetTransactionsUseCase(get()) }
    factory<TransactionsDataSource> { TransactionsDataSourceImpl(get()) }}