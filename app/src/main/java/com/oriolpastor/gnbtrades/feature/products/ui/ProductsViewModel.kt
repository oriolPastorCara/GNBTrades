package com.oriolpastor.gnbtrades.feature.products.ui

import androidx.lifecycle.*
import com.oriolpastor.gnbtrades.base.ui.navigation.Navigator
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.common.onError
import com.oriolpastor.gnbtrades.common.onSuccess
import com.oriolpastor.gnbtrades.feature.products.domain.local.SaveProductsUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.local.SaveRatesUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.remote.GetRatesUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.remote.GetProductsTransactionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsViewModel(
    private val navigator: Navigator,
    private val getRatesUseCase: GetRatesUseCase,
    private val getProductsTransactionsUseCase: GetProductsTransactionsUseCase,
    private val saveProductsUseCase: SaveProductsUseCase,
    private val saveRatesUseCase: SaveRatesUseCase,
) : ViewModel() {

    private val _productsList = MutableLiveData<List<Product>>()
    val productsList: LiveData<List<String>> =
        _productsList.map { it.map { data -> data.sku } }


    private val _isLoadingProducts = MutableLiveData(true)
    private val _isLoadingRates = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoadingProducts.asFlow().combine(
        _isLoadingRates.asFlow()
    ) { products, rates ->
        return@combine products && rates
    }.asLiveData()

    init {
        getRatesList()
        getTransactionsList()
    }

    private fun getRatesList() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                getRatesUseCase.invoke(Unit)
            }.onSuccess {
                _isLoadingRates.postValue(false)
                saveRatesToDB(it)
            }.onError {
                _isLoadingRates.postValue(false)
            }
        }
    }

    private fun getTransactionsList() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                getProductsTransactionsUseCase.invoke(Unit)
            }.onSuccess {
                _productsList.postValue(it)
                _isLoadingProducts.postValue(false)
                saveProductsToDB(it)
            }.onError {
                _isLoadingProducts.postValue(false)
            }
        }
    }

    private fun saveProductsToDB(products: List<Product>) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                saveProductsUseCase.invoke(products)
            }
        }
    }

    private fun saveRatesToDB(rates: List<Rate>) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                saveRatesUseCase.invoke(rates)
            }
        }
    }

    fun navigateToTransactionDetail(product: String) {
        navigator.goTo(ProductsFragmentDirections.navigateFromProductsToTransactionDetail(product))
    }
}