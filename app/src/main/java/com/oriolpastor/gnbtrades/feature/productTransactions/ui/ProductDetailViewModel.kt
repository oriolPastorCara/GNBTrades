package com.oriolpastor.gnbtrades.feature.productTransactions.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.common.onSuccess
import com.oriolpastor.gnbtrades.feature.productTransactions.domain.GetLocalProductTransactionsUseCase
import com.oriolpastor.gnbtrades.feature.productTransactions.domain.GetLocalRatesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import java.math.RoundingMode

class ProductDetailViewModel(
    private val productId: String,
    private val getLocalRatesUseCase: GetLocalRatesUseCase,
    private val getLocalProductTransactionsUseCase: GetLocalProductTransactionsUseCase,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private val _product: MutableSharedFlow<Product> = MutableSharedFlow(extraBufferCapacity = 1)
    private val _rateList = MutableSharedFlow<List<Rate>>(extraBufferCapacity = 1)
    val transactionsList: LiveData<List<BigDecimal>> =
        _product.combine(_rateList) { product, rateList ->
            val parsedList = mutableListOf<BigDecimal>()
            if (product.transactions.isNotEmpty() && rateList.isNotEmpty()) {
                product.transactions.map {
                    parsedList.add(
                        BigDecimal(
                            findEuroConversion(
                                it.currency,
                                it.amount,
                                rateList
                            ).toString()
                        ).setScale(2, RoundingMode.HALF_EVEN)
                    )
                }
            }
            return@combine parsedList
        }.asLiveData()
    val totalSumOfTransactions: LiveData<BigDecimal> =
        transactionsList.map { transactionsAmountList ->
            transactionsAmountList.sumOf { it }
        }

    fun findEuroConversion(
        transactionCurrency: String,
        transactionAmount: String,
        rateList: List<Rate>
    ): Float {
        if (transactionCurrency == EURO_CURRENCY) return transactionAmount.toFloat()
        var transactionValue = 0F
        val currentCurrencyRates = rateList.filter { it.from == transactionCurrency }

        val directConversion = currentCurrencyRates.find { it.to == EURO_CURRENCY }
        directConversion?.let {
            return transactionAmount.toFloat() * it.rate
        }

        if (transactionValue == 0F) {
            var i = 0
            if (transactionValue == 0F) {
                val rate = currentCurrencyRates[i]
                i++
                transactionValue = findEuroConversion(
                    rate.to,
                    (transactionAmount.toFloat() * rate.rate).toString(),
                    rateList,
                )
            }
        }
        return transactionValue
    }

    init {
        getRatesList()
        getProductTransactionsList()
    }

    private fun getRatesList() {
        viewModelScope.launch {
            withContext(coroutineDispatcher) {
                getLocalRatesUseCase.invoke(Unit)
            }.onSuccess {
                _rateList.tryEmit(it)
            }
        }
    }

    private fun getProductTransactionsList() {
        viewModelScope.launch {
            withContext(coroutineDispatcher) {
                getLocalProductTransactionsUseCase.invoke(productId)
            }.onSuccess {
                _product.tryEmit(it)
            }
        }
    }

    companion object {
        const val EURO_CURRENCY = "EUR"
    }
}
