package com.oriolpastor.gnbtrades.common.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.local.entities.Rate
import com.oriolpastor.gnbtrades.common.local.entities.TransactionsConverter

@Database(entities = [Product::class, Rate::class], version = 1)
@TypeConverters(TransactionsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
    abstract fun ratesDao(): RatesDao
}
