package com.example.gstore_android.data.repository

import com.example.gstore_android.data.models.Product

interface CartRepoInterface {

    suspend fun addToCart(product: Product ) : Boolean
    suspend fun  getItems() : List<Product>?
    suspend fun removeOneItwm(name: String): List<Product>?
    suspend fun clearCart(): List<Product>?

}