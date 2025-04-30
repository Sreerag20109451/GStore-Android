package com.example.gstore_android.data.dao

import com.example.gstore_android.data.models.Category
import com.example.gstore_android.data.models.Product

interface ProductDAO {

    suspend fun getAllProducts() : List<Product>?
    suspend fun getProductsByCategory(category : Category) : List<Product>?
}