package com.oriolpastor.gnbtrades.base

import com.oriolpastor.gnbtrades.base.ui.AppConfig
import com.oriolpastor.gnbtrades.base.ui.navigation.Navigator
import com.oriolpastor.gnbtrades.common.remote.ConfigurationProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module

val baseModule = module {
    single<ConfigurationProvider> { AppConfig() }
    single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
    single { Navigator() }
}