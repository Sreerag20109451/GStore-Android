package com.example.gstore_android.ui.components.products

import ProductCard
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gstore_android.data.models.Category
import com.example.gstore_android.data.models.Product
import com.example.gstore_android.viewmodels.ProductsViewModel
import products


@SuppressLint("UnrememberedMutableState")
@Composable
fun CategoryCard(
    categoryName: Category, productsViewModel : ProductsViewModel = hiltViewModel<ProductsViewModel>()
) {


    val isLoading by productsViewModel.isLoading
    val products by productsViewModel.catProducts


    LaunchedEffect(categoryName) {

        productsViewModel.getProductsByCategories(categoryName)

    }

    Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {

        Text(text=categoryName.toString(), fontSize = 20.sp, fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(20.dp))
        LazyRow(modifier = Modifier.padding(start = 8.dp, top = 8.dp)) {

            Log.d("ProductsCheck", "${products}")
            if(products!=null){
                Log.d("ProductsNotNull", "${products}")
                items(products as List<Product?>) {
                        product ->
                    ProductCard(product!!)

                }
            }

        }




    }
}