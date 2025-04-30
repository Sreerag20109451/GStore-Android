package com.example.gstore_android.data.dao

import android.util.Log
import com.example.gstore_android.data.models.Order
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestoreSettings
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OrderDAOImpl @Inject constructor(val firestore: FirebaseFirestore) : OrderDAOInterface{
    override suspend fun orderItem(order: Order): Boolean {
        try{
            firestore.collection("Orders").document().set(order).await()
            return true
        }
        catch(exc : Exception){
            Log.d("EXCCCC", exc.message!!)
        }

      return false

    }

    override suspend fun cancelOrder(orderId: String): Boolean {

        try{
            firestore.collection("Orders")
            return true
        }
        catch(exc : Exception){
            Log.d("EXCCCC", exc.message!!)
        }

        return false

    }

    override suspend fun getOrderForUser(userId: String): List<Order>? {

        try{
            val ordersforuser = firestore.collection("Orders").whereEqualTo("userId", userId).get().await()
            return  ordersforuser.toObjects(Order::class.java)
        }
        catch(exc : Exception){
            Log.d("EXCCCC", exc.message!!)
            return null
        }

        return null

    }


}