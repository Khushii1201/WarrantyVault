package com.example.warrantyvault.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(product: Product)

    @Query("SELECT * FROM products ORDER BY purchaseDate DESC")
    suspend fun getAll(): List<Product>

    @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Product?

    @Query("SELECT COUNT(*) FROM products")
    suspend fun count(): Int
}
