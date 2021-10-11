package com.oriolpastor.gnbtrades.common.local

import androidx.room.*
import com.oriolpastor.gnbtrades.common.local.entities.Product
import com.oriolpastor.gnbtrades.common.local.entities.Rate

@Dao
interface RatesDao {
    @Query("SELECT * FROM rates")
    suspend fun getRates(): List<Rate>

    @Transaction
    suspend fun replaceAll(rates: List<Rate>) {
        deleteAll()
        insertAll(rates)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rates: List<Rate>)

    @Query("DELETE FROM rates")
    fun deleteAll()
}