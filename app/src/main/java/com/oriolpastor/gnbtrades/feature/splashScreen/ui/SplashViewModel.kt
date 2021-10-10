package com.oriolpastor.gnbtrades.feature.splashScreen.ui

import com.oriolpastor.gnbtrades.R
import com.oriolpastor.gnbtrades.base.domain.GenericErrors
import com.oriolpastor.gnbtrades.base.ui.navigation.Navigator
import com.oriolpastor.gnbtrades.common.onError
import com.oriolpastor.gnbtrades.common.onSuccess
import com.oriolpastor.gnbtrades.feature.splashScreen.domain.GetRatesUseCase
import com.oriolpastor.gnbtrades.feature.splashScreen.domain.GetTransactionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SplashViewModel(
    private val navigator: Navigator,
    private val getRatesUseCase: GetRatesUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
) : BaseViewModel<Int>() {

    init {
        getRatesList()
        getTransactionsList()
    }

    private fun getRatesList() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                getRatesUseCase.invoke(Unit)
            }.onSuccess {
                //TODO: SaveToRoom
            }.onError {
                onRequestError(it)
            }
        }
    }

    private fun getTransactionsList() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                getTransactionsUseCase.invoke(Unit)
            }.onSuccess {
                //TODO: SaveToRoom
            }.onError {

            }
        }
    }

    private fun onRequestError(error: GenericErrors) {
        when (error) {
            GenericErrors.NETWORK_ERROR -> emitEvent(R.string.connetion_error)
            GenericErrors.GENERIC_ERROR -> emitEvent(R.string.generic_error)
        }
    }
}