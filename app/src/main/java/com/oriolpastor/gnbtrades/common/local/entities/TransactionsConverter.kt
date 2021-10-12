package com.oriolpastor.gnbtrades.common.local.entities

import androidx.room.TypeConverter
import com.oriolpastor.gnbtrades.feature.products.domain.remote.models.TransactionData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class TransactionsConverter() {

    @TypeConverter
    fun stringToTransaction(data: String?): List<TransactionData> {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        data?.let {
            val type = Types.newParameterizedType(List::class.java, TransactionData::class.java)
            val adapter = moshi.adapter<List<TransactionData>>(type)
            return adapter.fromJson(data) ?: listOf()
        } ?: run {
            return listOf()
        }
    }

    @TypeConverter
    fun transactionsToString(data: List<TransactionData>): String {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, TransactionData::class.java)
        val adapter: JsonAdapter<List<TransactionData>> = moshi.adapter(type)
        return adapter.toJson(data)
    }
}
