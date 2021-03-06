package com.oriolpastor.gnbtrades.feature.productTransactions

import com.oriolpastor.gnbtrades.feature.productTransactions.domain.GetLocalProductTransactionsUseCase
import com.oriolpastor.gnbtrades.feature.productTransactions.domain.GetLocalRatesUseCase
import com.oriolpastor.gnbtrades.feature.productTransactions.ui.ProductDetailViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val transactionDetailModule = module {
    viewModel { (productID: String) ->
        ProductDetailViewModel(
            productID,
            get(),
            get(),
            Dispatchers.Default
        )
    }
    factory { GetLocalRatesUseCase(get()) }
    factory { GetLocalProductTransactionsUseCase(get()) }
}
