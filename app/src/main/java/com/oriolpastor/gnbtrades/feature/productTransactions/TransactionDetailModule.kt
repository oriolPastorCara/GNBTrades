package com.oriolpastor.gnbtrades.feature.productTransactions

import com.oriolpastor.gnbtrades.feature.productTransactions.ui.TransactionDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val transactionDetailModule = module {
    viewModel {
        TransactionDetailViewModel()
    }
}