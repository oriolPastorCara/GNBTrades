package com.oriolpastor.gnbtrades.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.oriolpastor.gnbtrades.base.ui.navigation.Navigator
import com.oriolpastor.gnbtrades.common.MyResult
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.feature.products.domain.local.SaveProductsUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.local.SaveRatesUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.remote.GetProductsTransactionsUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.remote.GetRatesUseCase
import com.oriolpastor.gnbtrades.feature.products.domain.remote.models.TransactionData
import com.oriolpastor.gnbtrades.feature.products.ui.ProductsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProductViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @OptIn(ObsoleteCoroutinesApi::class)
    val mainThreadSurrogate = newSingleThreadContext("UI thread")

    val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Unconfined

    private lateinit var productsViewModel: ProductsViewModel

    @Mock
    private lateinit var getRatesUseCase: GetRatesUseCase

    @Mock
    private lateinit var getProductsUseCase: GetProductsTransactionsUseCase

    @Mock
    private lateinit var saveProductsUseCase: SaveProductsUseCase

    @Mock
    private lateinit var saveRatesUseCase: SaveRatesUseCase

    @Mock
    private lateinit var navigator: Navigator

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun config() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun onFinished() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun testGetAllTransactions() = runBlocking {
        whenever(getProductsUseCase.invoke(any())).thenReturn(
            MyResult.Success(getProductsList)
        )

        whenever(getRatesUseCase.invoke(any())).thenReturn(
            MyResult.Success(getCurrencyList)
        )

        productsViewModel = ProductsViewModel(
            navigator,
            getRatesUseCase,
            getProductsUseCase,
            saveProductsUseCase,
            saveRatesUseCase,
            coroutineDispatcher,
        )

        productsViewModel.productsList.observeForever {
            Assert.assertTrue(productsViewModel.productsList.value?.size == 2)
            Assert.assertTrue(
                productsViewModel.productsList.value?.containsAll(listOf("aaa", "bbb")) ?: false
            )
        }
    }

    private val getProductsList = listOf(
        Product(
            "aaa",
            listOf(
                TransactionData("2,5", "EUR"),
                TransactionData("24", "USD")
            )
        ),
        Product(
            "bbb",
            listOf(
                TransactionData("2,5", "EUR"),
                TransactionData("4", "CAD")
            )
        )
    )

    private val getCurrencyList = listOf(
        Rate(from = "USD", to = "EUR", rate = 2F),
        Rate(from = "CAD", to = "USD", rate = 0.5F)
    )
}
