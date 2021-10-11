package com.oriolpastor.gnbtrades.common.local

import androidx.room.*
import com.oriolpastor.gnbtrades.common.local.entities.Product

@Dao
interface ProductsDao {
    @Query("SELECT sku FROM products")
    suspend fun getAllProductsIds(): List<String>

    @Query("SELECT * FROM products WHERE sku LIKE :sku")
    suspend fun getAllProductsBySku(sku: String): Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)

    @Transaction
    suspend fun replaceAll(products: List<Product>) {
        deleteAll()
        insertAll(products)
    }

    @Query("DELETE FROM products")
    fun deleteAll()
}