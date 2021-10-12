package com.oriolpastor.gnbtrades.feature.productTransactions.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.oriolpastor.gnbtrades.R
import com.oriolpastor.gnbtrades.databinding.TransactionItemListBinding
import java.math.BigDecimal

class TransactionsListAdapter(
    private var transactionsList: List<BigDecimal>
) : RecyclerView.Adapter<TransactionsListAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: TransactionItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(amount: BigDecimal) {
            binding.transactionListItemAmount.text =
                binding.transactionListItemAmount.context.getString(
                    R.string.euro_currency,
                    amount
                )
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

    fun updateList(transactionsList: List<BigDecimal>) {
        this.transactionsList = transactionsList
    }
}
