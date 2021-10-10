package com.oriolpastor.gnbtrades.feature.products.ui

import androidx.lifecycle.*
import com.oriolpastor.gnbtrades.base.ui.navigation.Navigator
import com.oriolpastor.gnbtrades.common.onError
import com.oriolpastor.gnbtrades.common.onSuccess
import com.oriolpastor.gnbtrades.feature.splashScreen.domain.GetRatesUseCase
import com.oriolpastor.gnbtrades.feature.splashScreen.domain.GetTransactionsUseCase
import com.oriolpastor.gnbtrades.feature.splashScreen.domain.TransactionData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsViewModel(
    private val navigator: Navigator,
    private val getRatesUseCase: GetRatesUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
) : ViewModel() {

    private val _transactionsList = MutableLiveData<Map<String, List<TransactionData>>>()
    val transactionsList: LiveData<List<String>> =
        _transactionsList.map { value -> value.toList().map { it.first } }

    init {
        getRatesList()
        getTransactionsList()
    }

    private fun getRatesList() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                getRatesUseCase.invoke(Unit)
            }.onSuccess {
            }.onError {
            }
        }
    }

    private fun getTransactionsList() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                getTransactionsUseCase.invoke(Unit)
            }.onSuccess {
                _transactionsList.postValue(it)
            }.onError {

            }
        }
    }

    fun navigateToTransactionDetail(product: String) {
        navigator.goTo(ProductsFragmentDirections.navigateFromProductsToTransactionDetail(product))
    }
}