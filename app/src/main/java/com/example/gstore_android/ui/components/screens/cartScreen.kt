package com.example.gstore_android.ui.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.gstore_android.data.models.Product
import com.example.gstore_android.viewmodels.CartViewModel

@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val cartItems = cartViewModel.cartItems.value
    val totalPrice = cartViewModel.carttotal.value
    val isLoading = cartViewModel.isLoading.value



    Scaffold(
        bottomBar = {
            if (cartItems!=null) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total: ₹%.2f".format(totalPrice),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Button(onClick = { cartViewModel.placeOrder()}) {
                            Text("Checkout")
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (cartItems==null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Your cart is empty.")
            }
        } else {
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                items(cartItems) { product ->
                    ProductCartItem(product = product, cartViewModel)
                    Divider()
                }
            }
        }
    }
}

@Composable
fun ProductCartItem(product: Product, cartViewModel: CartViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        if (!product.imageUrl.isNullOrEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 8.dp)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = product.name ?: "Unnamed",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Price: ₹${product.price ?: 0.0}",
                style = MaterialTheme.typography.bodyMedium
            )

            IconButton(
                onClick = { cartViewModel.removeOneItem(product.name) }
            ) {
                Icon(
                    imageVector = Icons.Default.RemoveCircleOutline,
                    contentDescription = "Remove"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Remove",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
