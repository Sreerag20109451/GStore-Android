package com.example.gstore_android.data.repository

import android.util.Log
import com.example.gstore_android.data.dao.ProductDAOImpl
import com.example.gstore_android.data.models.Category
import com.example.gstore_android.data.models.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class ProductsRepoImpl @Inject constructor(val firestore: FirebaseFirestore , val storage: FirebaseStorage, val productDAOImpl: ProductDAOImpl) : ProductsRepo {
    override suspend fun getAllproducts(): List<Product>? {


        return productDAOImpl.getAllProducts()
    }

    override suspend fun gerAllProductsByCategories(category : Category): List<Product>? {

        val prods =  productDAOImpl.getProductsByCategory(category)
        Log.d("RepoProds-At-Repo", "${prods}")
        return  prods
    }


}