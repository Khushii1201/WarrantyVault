package com.example.warrantyvault.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productName: String,
    val brand: String,
    val price: Double,
    val purchaseDate: Long,
    val warrantyMonths: Int,
    val expiryDate: Long,
    val invoicePath: String? = null
)
