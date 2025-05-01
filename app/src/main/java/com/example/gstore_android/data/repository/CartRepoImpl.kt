package com.example.gstore_android.data.repository

import com.example.gstore_android.data.dao.CartDAOImpl
import com.example.gstore_android.data.models.Product
import javax.inject.Inject

class CartRepoImpl @Inject constructor(val cartDAOImpl: CartDAOImpl) : CartRepoInterface {
    override suspend fun addToCart(product: Product): Boolean {
        return cartDAOImpl.addToCart(product)
    }

    override suspend fun getItems(): List<Product>? {
        return  cartDAOImpl.getItems()
    }

    override suspend fun removeOneItwm(name: String): List<Product>? {
       return  cartDAOImpl.removeOneItwm(name)
    }

    override suspend fun clearCart(): Boolean {
        return  cartDAOImpl.clearCart()
    }


}