package com.example.gstore_android.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gstore_android.data.models.Order
import com.example.gstore_android.data.models.Product
import com.example.gstore_android.data.repository.CartRepoImpl
import com.example.gstore_android.data.repository.OrderRepoImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val orderRepoImpl: OrderRepoImpl,
    private val cartrepo: CartRepoImpl,
    private val auth: FirebaseAuth,
) : ViewModel() {

    var uid = mutableStateOf<String?>(auth.currentUser?.uid)
    var ordersforuser = mutableStateOf<List<Order>?>(null)
    var cartItems = mutableStateOf<List<Product>?>(null)
    val notification = mutableStateOf<String?>(null)
    val carttotal = mutableStateOf<Double?>(0.0)
    val isLoading = mutableStateOf(false)

    init {
        val user = auth.currentUser
        if (user != null) {
            uid.value = user.uid
            viewModelScope.launch {
                val items = cartrepo.getItems()
                val orders = orderRepoImpl.getOrderForUser(uid.value!!)
                ordersforuser.value = orders
                cartItems.value = items
                if (items != null) {
                    carttotal.value = totalCartPrice(items)
                }
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            val added = cartrepo.addToCart(product)
            if (!added) {
                notification.value = "Error adding to cart"
                return@launch
            }
            cartItems.value = cartrepo.getItems()
            if (cartItems.value != null) {
                carttotal.value = totalCartPrice(cartItems.value!!)
            }
            notification.value = "Added to cart"
        }
    }

    fun removeOneItem(name: String) {
        viewModelScope.launch {
            val updatedItems = cartrepo.removeOneItwm(name)
            if (updatedItems != null) {
                cartItems.value = updatedItems
                if (cartItems.value != null) {
                    carttotal.value = totalCartPrice(updatedItems)
                }
            } else {
                notification.value = "Error removing item from cart"
            }
        }
    }

    fun placeOrder() {
        if (cartItems.value != null) {
            val order = Order(
                id = UUID.randomUUID().toString(),
                products = cartItems.value!!,
                totalPrice = carttotal.value!!,
                orderDate = Date(),
                userId = uid.value!!,
                userEmail = auth.currentUser?.email!!
            )
            viewModelScope.launch {
                try {
                    val orders = orderRepoImpl.orderItem(order)
                    if (orders) {
                        notification.value = "Order placed Successfully"
                    }
                    delay(300)
                    getOrdersByUser()
                    delay(300)
                    clearCart()
                } catch (ex: Exception) {
                    notification.value = "Error placing the order"
                }
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            val cleared = cartrepo.clearCart()
            if (cleared) {
                cartItems.value = null
            } else {
                notification.value = "Error clearing cart"
            }
        }
    }

    fun getOrdersByUser() {
        viewModelScope.launch {
            try {
                val orders = orderRepoImpl.getOrderForUser(uid.value!!)
                ordersforuser.value = orders
            } catch (ex: Exception) {
                notification.value = "Error fetching orders"
            }
        }
    }

    fun totalCartPrice(cartItems: List<Product>): Double {
        var total = 0.0
        cartItems.forEach { product ->
            total += product.price ?: 0.0
        }
        return total
    }
}
