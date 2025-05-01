package com.example.gstore_android.data.repository

import com.example.gstore_android.data.models.Order

interface OrderRepoInterface {

    suspend fun orderItem(order : Order) : Boolean
    suspend fun getOrderForUser(userId: String) : List<Order>?
}