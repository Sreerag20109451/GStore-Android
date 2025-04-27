package com.example.gstore_android.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gstore_android.data.models.Category
import com.example.gstore_android.data.models.Product
import com.example.gstore_android.data.repository.ProductsRepo
import com.example.gstore_android.data.repository.ProductsRepoImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductsViewModel @Inject constructor(val firestore: FirebaseFirestore, val storage: FirebaseStorage, val productsRepo: ProductsRepoImpl) : ViewModel(){

    val isLoading = mutableStateOf(false)
    val products = mutableStateOf<List<Product>?>(null)
    val catProducts =  mutableStateOf<List<Product>?>(null)


    init {
        isLoading.value = true
        viewModelScope.launch {
            try {
                products.value = productsRepo.getAllproducts()
                isLoading.value = false
            }
            catch(e : Exception) {
                isLoading.value = false
            }
        }


    }


    fun getProductsByCategories(category: Category) {
        val products = mutableStateOf<List<Product>?>(null)
        viewModelScope.launch {
            catProducts.value = productsRepo.gerAllProductsByCategories(category)
            Log.d("PROD_VM", "${catProducts.value} are the prods")

        }

    }


}