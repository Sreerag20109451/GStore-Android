package com.example.gstore_android.data.models





data class Products(val name : String, val category: Category, val discount: Double? = 0.0, val quantity : Double = 1.0 , val price : Double , val imageUrl : String? = null)
{

    fun toMap(): Map<String, Any> {
        return mapOf(
            "name" to name,
            "category" to category,
            "discount" to discount,
            "quantity" to quantity,
            "price" to price,
            "imageUrl" to imageUrl
        ) as Map<String, Any>
    }
}