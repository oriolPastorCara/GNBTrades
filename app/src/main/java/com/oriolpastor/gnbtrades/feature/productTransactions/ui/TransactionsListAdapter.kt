package com.oriolpastor.gnbtrades.feature.productTransactions.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.oriolpastor.gnbtrades.R
import com.oriolpastor.gnbtrades.databinding.ProductsItemListBinding
import com.oriolpastor.gnbtrades.databinding.TransactionItemListBinding
import com.oriolpastor.gnbtrades.feature.products.domain.remote.models.TransactionData
import com.oriolpastor.gnbtrades.feature.products.ui.ProductsListAdapter

class TransactionsListAdapter(
    private var transactionsList: List<Float>
) : RecyclerView.Adapter<TransactionsListAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: TransactionItemListBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(amount: Float) {
            binding.transactionListItemAmount.text = amount.toString()

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TransactionsListAdapter.ViewHolder {
        val transactionItemBinding: TransactionItemListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.transaction_item_list,
                parent,
                false,
            )
        return ViewHolder(transactionItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(transactionsList[position])

    override fun getItemCount() = transactionsList.size

    fun updateList(transactionsList: List<Float>) {
        this.transactionsList = transactionsList
    }
}