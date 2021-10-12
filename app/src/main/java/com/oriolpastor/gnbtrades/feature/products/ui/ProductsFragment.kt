package com.oriolpastor.gnbtrades.feature.products.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.oriolpastor.gnbtrades.R
import com.oriolpastor.gnbtrades.base.ui.BaseFragment
import com.oriolpastor.gnbtrades.databinding.ProductsFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsFragment :
    BaseFragment<ProductsFragmentBinding, ProductsViewModel>() {

    override val layoutRes: Int = R.layout.products_fragment
    override val viewModel: ProductsViewModel by viewModel()

    private lateinit var productsAdapter: ProductsListAdapter

    override fun init(binding: ProductsFragmentBinding) {
        binding.viewModel = viewModel
        setupList(binding)
    }

    private fun setupList(binding: ProductsFragmentBinding) {
        viewModel.productsList.observe(viewLifecycleOwner, {
            if (binding.transactionsList.adapter == null) {
                val linearLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.transactionsList.layoutManager = linearLayoutManager
                productsAdapter = ProductsListAdapter(
                    it,
                    ::onListClick,
                )
                binding.transactionsList.adapter = productsAdapter
            } else {
                productsAdapter.updateList(it)
                productsAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun onListClick(product: String) {
        viewModel.navigateToTransactionDetail(product)
    }
}
