package com.oriolpastor.gnbtrades.feature.splashScreen.domain

import com.oriolpastor.gnbtrades.base.domain.GenericErrors
import com.oriolpastor.gnbtrades.base.domain.UseCase
import com.oriolpastor.gnbtrades.common.MyResult

class GetTransactionsUseCase(private val transactionsDataSource: TransactionsDataSource) :
        UseCase<Unit, MyResult<Map<String, List<TransactionData>>, GenericErrors>> {
        override suspend fun invoke(params: Unit) :
                MyResult<Map<String, List<TransactionData>>, GenericErrors> =
            transactionsDataSource.getTransactionsList()
    }
