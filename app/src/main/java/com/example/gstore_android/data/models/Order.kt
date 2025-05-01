package com.example.gstore_android.data.models

import java.util.*

data class Order(
    val id: String = "",
    val products: List<Product> = emptyList(),
    val totalPrice: Double = 0.0,
    val orderDate: Date = Date(),
    val userId: String = "",
    val userEmail: String = ""
)
