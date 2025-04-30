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
    val displayProducts = mutableStateOf<List<Product>?>(null)
    val catProducts = mutableStateOf<Map<Category, List<Product>>>(emptyMap())


    init {
        isLoading.value = true
        viewModelScope.launch {
            try {
                products.value = productsRepo.getAllproducts()
                displayProducts.value = products.value
                isLoading.value = false
            }
            catch(e : Exception) {
                isLoading.value = false
            }
        }


    }


    fun getProductsByCategories(category: Category) {
        isLoading.value = true
        viewModelScope.launch {
            val products = productsRepo.gerAllProductsByCategories(category)
            catProducts.value = catProducts.value.toMutableMap().apply {
                put(category, products ?: emptyList())
            }
            isLoading.value =false
            Log.d("PROD_VM", "${catProducts.value} are the prods")

        }

    }

    fun searchByNameOrCategories(searchStr : String) {

        displayProducts.value = products.value

        if (searchStr.isBlank()) {
            // Reset to show all products if search string is empty
            displayProducts.value = products.value // Replace 'allProducts' with the original list of all products
            return
        }

        if(displayProducts.value ==null) {

            displayProducts.value = null
            return
        }


        val productsTobeDisplayedByname = displayProducts.value?.filter {
            product -> product.name.lowercase().contains(searchStr.lowercase())
        }

        val productsTobeDisplayedByCategory = displayProducts.value?.filter {
            product -> product.category.toString().lowercase().contains(searchStr.lowercase())
        }


        val displaylist = (productsTobeDisplayedByname ?: emptyList()) + (productsTobeDisplayedByCategory ?: emptyList())
        displayProducts.value = displaylist as List<Product>?

    }

    fun sortByPrice(condition: String){


        val sortedProducts = when(condition){
            "asc" ->  displayProducts.value?.sortedBy { product -> product.price }
            "desc" -> displayProducts.value?.sortedByDescending { product -> product.price  }
            else -> products
        }

        displayProducts.value = sortedProducts as List<Product>?


    }


}