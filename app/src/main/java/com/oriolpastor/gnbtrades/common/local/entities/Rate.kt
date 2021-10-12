package com.oriolpastor.gnbtrades.common.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
data class Rate(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val from: String,
    val to: String,
    val rate: Float,
)
