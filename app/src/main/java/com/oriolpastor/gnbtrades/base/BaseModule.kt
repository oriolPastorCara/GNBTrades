package com.oriolpastor.gnbtrades.base

import com.oriolpastor.gnbtrades.base.ui.navigation.Navigator
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module

val baseModule = module {
    single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
    single { Navigator() }
}