package com.oriolpastor.gnbtrades.common.remote

import org.koin.dsl.module

val apiModule = module {
    single { ApiProvider.provideApi(get()) }
    single { ApiProvider.provideRetrofit(get()) }
    single { ApiProvider.provideMoshiConverter() }
}
