package com.example.gstore_android.data.dao

import android.util.Log
import com.example.gstore_android.data.models.Category
import com.example.gstore_android.data.models.Product
import com.example.gstore_android.data.models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductDAOImpl @Inject constructor(val firestore: FirebaseFirestore) : ProductDAO{
    override suspend fun getAllProducts(): List<Product>? {
        try{
            val productDocumentSnapshot =  firestore.collection("PRODUCTS").get().await()
            Log.d("ProdAll" , "${productDocumentSnapshot}")
            if(productDocumentSnapshot.size()>0)   return productDocumentSnapshot.toObjects(Product::class.java)
        }
        catch(e : Exception) {

            Log.d("ProdAll" , "Not_Working")

            return  null
        }

        return null
    }


    override suspend fun getProductsByCategory(category: Category) : List<Product>? {

        try{
            val productDocumentSnapshot =  firestore.collection("PRODUCTS")
            val productsOfCategory =  productDocumentSnapshot.whereEqualTo("category", category.toString()).get().await()
            Log.d("Cat_Prod" , "${productsOfCategory}")
            if(productsOfCategory.size()>0)  {
                Log.d("Cat_Prod-not0" , "${productsOfCategory.documents}")
                return productsOfCategory.toObjects(Product::class.java)
            }
        }
        catch(e : Exception) {

            Log.d("Cat_Prod_Check" , e.message!!)

            return  null
        }

        return null
    }







}