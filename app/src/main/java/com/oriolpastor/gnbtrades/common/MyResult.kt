package com.oriolpastor.gnbtrades.common

sealed class MyResult<out S, out E> {
    data class Success<out S>(val value: S) : MyResult<S, Nothing>()
    data class Error<out E>(val error: E) : MyResult<Nothing, E>()
}

inline infix fun <S, E> MyResult<S, E>.onSuccess(action: (S) -> Unit): MyResult<S, E> {
    if (this is MyResult.Success) {
        action(value)
    }
    return this
}

inline infix fun <S, E> MyResult<S, E>.onError(action: (E) -> Unit): MyResult<S, E> {
    if (this is MyResult.Error) {
        action(error)
    }
    return this
}
