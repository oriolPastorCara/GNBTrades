package com.oriolpastor.gnbtrades.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.oriolpastor.gnbtrades.common.MyResult
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.feature.productTransactions.domain.GetLocalProductTransactionsUseCase
import com.oriolpastor.gnbtrades.feature.productTransactions.domain.GetLocalRatesUseCase
import com.oriolpastor.gnbtrades.feature.productTransactions.ui.ProductDetailViewModel
import com.oriolpastor.gnbtrades.feature.products.domain.remote.models.TransactionData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProductDetailViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @OptIn(ObsoleteCoroutinesApi::class)
    val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Unconfined
    private lateinit var productsViewModel: ProductDetailViewModel

    @Mock
    private lateinit var getLocalRatesUseCase: GetLocalRatesUseCase
    @Mock
    private lateinit var getLocalProductTransactionsUseCase: GetLocalProductTransactionsUseCase

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
    fun testGetAllTransactionsAmountToEUR() = runBlockingTest {
        whenever(getLocalProductTransactionsUseCase.invoke(any())).thenReturn(
            MyResult.Success(productsResult)
        )

        whenever(getLocalRatesUseCase.invoke(any())).thenReturn(
            MyResult.Success(currencyResult)
        )

        productsViewModel = ProductDetailViewModel(
            PRODUCT_ID,
            getLocalRatesUseCase,
            getLocalProductTransactionsUseCase,
            coroutineDispatcher,
        )

        productsViewModel.transactionsList.observeForever { transactionsList ->
            val correctList = listOf((2.5).toBigDecimal(), 49.toBigDecimal(), 10.toBigDecimal())
            Assert.assertTrue(
                productsViewModel.transactionsList.value?.containsAll(correctList) ?: false
            )
        }
    }

    @Test
    fun testGetSumOfAllTransactions() = runBlocking {
        whenever(getLocalProductTransactionsUseCase.invoke(any())).thenReturn(
            MyResult.Success(productsResult)
        )

        whenever(getLocalRatesUseCase.invoke(any())).thenReturn(
            MyResult.Success(currencyResult)
        )

        productsViewModel = ProductDetailViewModel(
            PRODUCT_ID,
            getLocalRatesUseCase,
            getLocalProductTransactionsUseCase,
            coroutineDispatcher,
        )
//        val flow = productsViewModel.totalSumOfTransactions(60.5.toBigDecimal())
//            flow.collect {
//            println(it)
//            Assert.assertTrue(it == 30.5.toBigDecimal())
//        }
//        coEvery {}
//        suspend
//        InitTransportPrefsUseCaseTest
//        MyPLlacesViewModelTest
    }

    @Test
    fun findEuroTransaction() = runBlocking {
        productsViewModel = ProductDetailViewModel(
            PRODUCT_ID,
            getLocalRatesUseCase,
            getLocalProductTransactionsUseCase,
            coroutineDispatcher,
        )
        val value1 = productsViewModel.findEuroConversion("CAD", "10", currencyResult)
        val value2 = productsViewModel.findEuroConversion("USD", "20", currencyResult)
        val value3 = productsViewModel.findEuroConversion("BTC", "10", currencyResult)
        println("return value1 = $value1")
        println("return value2 = $value2")
        println("return value3 = $value3")
        Assert.assertTrue(value1 == 10F)
        Assert.assertTrue(value2 == 40F)
        Assert.assertTrue(value3 == 50F)
    }

    private val productsResult =
        Product(
            PRODUCT_ID,
            listOf(
                TransactionData("2,5", "EUR"),
                TransactionData("24", "USD"),
                TransactionData("10", "CAD"),
            )
        )

    private val currencyResult = listOf(
        Rate(from = "USD", to = "EUR", rate = 2F),
        Rate(from = "CAD", to = "USD", rate = 0.5F),
        Rate(from = "BTC", to = "CAD", rate = 5F),
    )

    companion object {
        const val PRODUCT_ID = "FA333"
    }
}
