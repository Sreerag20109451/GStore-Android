package com.example.gstore_android.data.dao

import com.example.gstore_android.data.models.Order

interface OrderDAOInterface {

    suspend fun orderItem(order : Order) : Boolean
    suspend fun  cancelOrder(orderId: String) : Boolean
    suspend fun getOrderForUser(userId: String) : List<Order>?
}
