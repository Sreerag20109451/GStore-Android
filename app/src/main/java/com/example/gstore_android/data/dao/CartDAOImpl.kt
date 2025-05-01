package com.example.gstore_android.data.dao

import android.util.Log
import com.example.gstore_android.data.models.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.DateTime
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class CartDAOImpl @Inject constructor(val firestore: FirebaseFirestore, val auth : FirebaseAuth) : CartDAO {



    override suspend fun addToCart(product: Product): Boolean {
         val userId = FirebaseAuth.getInstance().currentUser?.uid
          val cartRef = firestore
            .collection("USERS")
            .document(userId!!)
            .collection("CART")
        try{
            cartRef.document().set(product).await()
            return true
        }
        catch (exc : Exception){
            Log.d("ADDCART_ERROR", "${exc.message}")
            return false

        }

    }

    override suspend fun getItems(): List<Product>? {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val cartRef = firestore
            .collection("USERS")
            .document(userId!!)
            .collection("CART")

        try{
            val resp = cartRef.get().await()
            return resp.toObjects(Product::class.java)
        }
        catch (exc : Exception){
            Log.d("ADDCART_ERROR", "${exc.message}")
            return null

        }
    }

    override suspend fun removeOneItwm(name : String): List<Product>? {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val cartRef = firestore
            .collection("USERS")
            .document(userId!!)
            .collection("CART")
        try{
            val resp = cartRef.whereEqualTo("name", name).get().await()
            if(resp.documents.isEmpty()) return  null
            val first = resp.documents.first()
            first.reference.delete().await()
            val cartItems = getItems()
            return  cartItems
        }
        catch (exc : Exception){
            Log.d("ADDCART_ERROR", "${exc.message}")
            return null

        }

    }
    override suspend fun clearCart(): Boolean {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val cartRef = firestore
            .collection("USERS")
            .document(userId!!)
            .collection("CART")

        return try {
            val resp = cartRef.get().await()

            if (resp.documents.isEmpty()) {
                Log.d("ADDCART_ERROR", "Cart is already empty.")
                false
            } else {
                resp.documents.forEach { document ->
                    document.reference.delete().await()
                    Log.d("REMOVE_SUCCESS", "Product removed: ${document.id}")
                }
                true // ✅ Return after all deletions
            }

        } catch (exc: Exception) {
            Log.d("ADDCART_ERROR", "Error clearing the cart: ${exc.message}")
            false
        }
    }





}