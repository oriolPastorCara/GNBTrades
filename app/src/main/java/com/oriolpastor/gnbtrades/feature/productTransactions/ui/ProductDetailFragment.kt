package com.oriolpastor.gnbtrades.feature.productTransactions.ui

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.oriolpastor.gnbtrades.R
import com.oriolpastor.gnbtrades.base.ui.BaseFragment
import com.oriolpastor.gnbtrades.databinding.ProductDetailFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.Locale

class ProductDetailFragment :
    BaseFragment<ProductDetailFragmentBinding, ProductDetailViewModel>() {
    private val args: ProductDetailFragmentArgs by navArgs()

    override val layoutRes: Int = R.layout.product_detail_fragment
    override val viewModel: ProductDetailViewModel by viewModel { parametersOf(args.productId) }

    private lateinit var transactionsListAdapter: TransactionsListAdapter

    override fun init(binding: ProductDetailFragmentBinding) {
        setupList(binding)
        binding.productDetailProductSelectedText.text =
            getString(
                R.string.product_id_transactions,
                args.productId.uppercase(Locale.getDefault()),
            )
    }

    private fun setupList(binding: ProductDetailFragmentBinding) {
        viewModel.transactionsList.observe(viewLifecycleOwner, {
            if (binding.productDetailTransactionList.adapter == null) {
                val linearLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.productDetailTransactionList.layoutManager = linearLayoutManager
                transactionsListAdapter = TransactionsListAdapter(it)
                binding.productDetailTransactionList.adapter = transactionsListAdapter
            } else {
                transactionsListAdapter.updateList(it)
                transactionsListAdapter.notifyDataSetChanged()
            }
        })
        viewModel.totalSumOfTransactions.observe(viewLifecycleOwner, {
            binding.productDetailTotalTransactionsAmount.text =
                binding.productDetailTotalTransactionsAmount.context.getString(
                    R.string.total_transactions_amount,
                    it
                )
        })
    }
}
