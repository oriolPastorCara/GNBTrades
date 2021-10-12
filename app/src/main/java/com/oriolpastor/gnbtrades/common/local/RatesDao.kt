package com.oriolpastor.gnbtrades.common.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
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
