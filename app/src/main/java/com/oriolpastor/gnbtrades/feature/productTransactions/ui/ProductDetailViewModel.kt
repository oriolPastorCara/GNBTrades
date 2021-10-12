package com.oriolpastor.gnbtrades.feature.productTransactions.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.common.onSuccess
import com.oriolpastor.gnbtrades.feature.productTransactions.domain.GetLocalProductTransactionsUseCase
import com.oriolpastor.gnbtrades.feature.productTransactions.domain.GetLocalRatesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import java.math.RoundingMode

class ProductDetailViewModel(
    private val productId: String,
    private val getLocalRatesUseCase: GetLocalRatesUseCase,
    private val getLocalProductTransactionsUseCase: GetLocalProductTransactionsUseCase
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    private val _rateList = MutableLiveData<List<Rate>>()
    val transactionsList: LiveData<List<BigDecimal>> =
        _product.asFlow().combine(_rateList.asFlow()) { product, rateList ->
            val parsedList = mutableListOf<BigDecimal>()
            Log.d("@@@", "combine")
            if (product.transactions.isNotEmpty() && rateList.isNotEmpty()) {
                product.transactions.map {
                    if (it.currency == EURO_CURRENCY) {
                        Log.d("@@@", "add euro transaction")
                        parsedList.add(
                            BigDecimal(it.amount).setScale(2, RoundingMode.HALF_EVEN)
                        )
                    } else {
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
            }
            return@combine parsedList
        }.asLiveData()
    val totalSumOfTransactions: LiveData<BigDecimal> = transactionsList.map {
        var totalTransactionsSum = BigDecimal.ZERO
        for (amount in it) {
            totalTransactionsSum += amount
        }
        totalTransactionsSum
    }

    private fun findEuroConversion(
        transactionCurrency: String,
        transactionAmount: String,
        rateList: List<Rate>
    ): Float {
        var transactionValue = 0F
        val currentCurrencyRates = rateList.filter { it.from == transactionCurrency }

        for (rate in currentCurrencyRates) {
            if (rate.to == EURO_CURRENCY) {
                return transactionAmount.toFloat() * rate.rate
            }
        }

        if (transactionValue == 0F) {
            var i = 0
            do {
                val rate = currentCurrencyRates[i]
                i++
                transactionValue = findEuroConversion(
                    rate.to,
                    (transactionAmount.toFloat() * rate.rate).toString(),
                    rateList,
                )
            } while (transactionValue == 0F)
        }
        return transactionValue
    }

    init {
        getRatesList()
        getProductTransactionsList()
    }

    private fun getRatesList() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                getLocalRatesUseCase.invoke(Unit)
            }.onSuccess {
                _rateList.postValue(it)
            }
        }
    }

    private fun getProductTransactionsList() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                getLocalProductTransactionsUseCase.invoke(productId)
            }.onSuccess {
                _product.postValue(it)
            }
        }
    }

    companion object {
        const val EURO_CURRENCY = "EUR"
    }
}
