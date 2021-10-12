package com.oriolpastor.gnbtrades.common.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oriolpastor.gnbtrades.feature.products.domain.remote.models.TransactionData

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val sku: String,
    @ColumnInfo
    val transactions: List<TransactionData>,
)
