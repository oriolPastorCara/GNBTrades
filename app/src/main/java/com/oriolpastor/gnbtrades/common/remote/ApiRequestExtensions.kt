package com.oriolpastor.gnbtrades.common.remote

import android.accounts.NetworkErrorException
import com.oriolpastor.gnbtrades.base.domain.GenericErrors
import com.oriolpastor.gnbtrades.common.MyResult
import java.lang.Exception

suspend fun <T> ApiService.execute(body: suspend ApiService.() -> T): MyResult<T, GenericErrors> =
    try {
        MyResult.Success(body())
    } catch (e: NetworkErrorException) {
        MyResult.Error(GenericErrors.NETWORK_ERROR)
    } catch (e: Exception) {
        MyResult.Error(GenericErrors.GENERIC_ERROR)
    }
