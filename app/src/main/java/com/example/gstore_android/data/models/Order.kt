package com.example.gstore_android.data.models

import com.google.type.DateTime
import java.util.Date


data class Order(val id : String, val products : List<Product>, val totalPrice : Double, val orderDate : Date, val userId : String, val userEmail : String)

