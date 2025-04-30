package com.example.gstore_android.data.repository

import com.example.gstore_android.data.models.Category
import com.example.gstore_android.data.models.Product

interface ProductsRepo {

    suspend fun getAllproducts() : List<Product>?
    suspend fun  gerAllProductsByCategories(category: Category) : List<Product>?
}