package com.example.gstore_android.ui.components.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.gstore_android.data.models.Order
import com.example.gstore_android.data.models.Product

@Composable
fun CartScreen(order: Order, onCheckoutClick: () -> Unit) {
    Scaffold(
        bottomBar = {
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
                        text = "Total: ₹${order.totalPrice}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Button(onClick = onCheckoutClick) {
                        Text("Checkout")
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(order.products) { product ->
                ProductCartItem(product = product)
                Divider()
            }
        }
    }
}

@Composable
fun ProductCartItem(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        if (product.imageUrl != null) {
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
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Qty: ${product.quantity} @ ₹${product.price}", style = MaterialTheme.typography.bodyMedium)
            if (product.discount != null && product.discount > 0.0) {
                Text(
                    text = "Discount: ₹${product.discount}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
