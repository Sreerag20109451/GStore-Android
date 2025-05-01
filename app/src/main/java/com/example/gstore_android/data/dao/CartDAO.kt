package com.example.gstore_android.data.dao

import com.example.gstore_android.data.models.Order
import com.example.gstore_android.data.models.Product

interface CartDAO {

    suspend fun addToCart(product: Product ) : Boolean
    suspend fun  getItems() : List<Product>?
    suspend fun removeOneItwm(name: String): List<Product>?
    suspend fun clearCart(): Boolean


}