package com.oriolpastor.gnbtrades.feature.products.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.oriolpastor.gnbtrades.R
import com.oriolpastor.gnbtrades.databinding.ProductsItemListBinding

class ProductsListAdapter(
    private var transactionsList: List<String>,
    private val onUserClick: ((String) -> Unit),
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ProductsItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.productListItemTitle.text = title
            binding.root.setOnClickListener {
                onUserClick(title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val transactionItemBinding: ProductsItemListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.products_item_list,
                parent,
                false,
            )
        return ViewHolder(transactionItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(transactionsList[position])

    override fun getItemCount() = transactionsList.size

    fun updateList(transactionsList: List<String>) {
        this.transactionsList = transactionsList
    }
}