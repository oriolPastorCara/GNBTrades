package com.oriolpastor.gnbtrades.base.domain

interface UseCase<P, R> {
    suspend operator fun invoke(params: P): R
}