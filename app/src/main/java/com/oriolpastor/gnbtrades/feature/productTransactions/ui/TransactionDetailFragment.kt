package com.oriolpastor.gnbtrades.feature.productTransactions.ui

import com.oriolpastor.gnbtrades.R
import com.oriolpastor.gnbtrades.base.ui.BaseFragment
import com.oriolpastor.gnbtrades.databinding.TransactionDetailFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TransactionDetailFragment :
    BaseFragment<TransactionDetailFragmentBinding, Int, TransactionDetailViewModel>() {
    override val layoutRes: Int = R.layout.transaction_detail_fragment
    override val viewModel: TransactionDetailViewModel by viewModel()

    override fun setupBinding(binding: TransactionDetailFragmentBinding) {

    }
}