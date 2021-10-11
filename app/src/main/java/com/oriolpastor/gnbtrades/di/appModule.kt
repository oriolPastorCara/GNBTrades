package com.oriolpastor.gnbtrades.di

import androidx.room.Room
import com.oriolpastor.gnbtrades.common.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val DATABASE = "main_database"
val appModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, DATABASE).build() }
}