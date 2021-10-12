package com.oriolpastor.gnbtrades.base.ui

import android.app.Application
import com.oriolpastor.gnbtrades.base.baseModule
import com.oriolpastor.gnbtrades.common.remote.apiModule
import com.oriolpastor.gnbtrades.di.appModule
import com.oriolpastor.gnbtrades.feature.productTransactions.transactionDetailModule
import com.oriolpastor.gnbtrades.feature.products.productsModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        appModule.single { applicationScope }
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    appModule,
                    baseModule,
                    appModule,
                    apiModule,
                    productsModule,
                    transactionDetailModule,
                )
            )
        }
    }
}
