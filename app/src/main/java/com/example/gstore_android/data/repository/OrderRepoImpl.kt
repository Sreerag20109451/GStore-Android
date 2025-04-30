package com.example.gstore_android.data.repository

import com.example.gstore_android.data.dao.OrderDAOImpl
import com.example.gstore_android.data.models.Order
import javax.inject.Inject

class OrderRepoImpl @Inject constructor(val orderDAOImpl: OrderDAOImpl) : OrderRepoInterface {
    override suspend fun orderItem(order: Order): Boolean {
        return orderDAOImpl.orderItem(order)
    }

    override suspend fun cancelOrder(orderId: String): Boolean {

        return orderDAOImpl.cancelOrder(orderId)
    }

    override suspend fun getOrderForUser(userId: String): List<Order>? {
        return orderDAOImpl.getOrderForUser(userId)
    }

}