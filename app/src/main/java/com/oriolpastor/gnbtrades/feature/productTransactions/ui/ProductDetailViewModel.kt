package com.oriolpastor.gnbtrades.feature.productTransactions.ui

import androidx.lifecycle.*
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.onSuccess
import com.oriolpastor.gnbtrades.feature.productTransactions.domain.GetLocalRatesUseCase
import com.oriolpastor.gnbtrades.feature.productTransactions.domain.GetLocalProductTransactionsUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.remote.models.TransactionData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailViewModel(
    private val productId: String,
    private val getLocalRatesUseCase: GetLocalRatesUseCase,
    private val getLocalProductTransactionsUseCase: GetLocalProductTransactionsUseCase
): ViewModel() {

    private val _transactionsList = MutableLiveData<Product>()
    val transactionsList: LiveData<List<Float>> =
        _transactionsList.map { it.transactions.map { it.amount.toFloat() } }

    init {
        getRatesList()
        getProductTransactionsList()
    }

    private fun getRatesList() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                getLocalRatesUseCase.invoke(Unit)
            }.onSuccess {

            }
        }
    }
    private fun getProductTransactionsList() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                getLocalProductTransactionsUseCase.invoke(productId)
            }.onSuccess {
                _transactionsList.postValue(it)
            }
        }
    }

}