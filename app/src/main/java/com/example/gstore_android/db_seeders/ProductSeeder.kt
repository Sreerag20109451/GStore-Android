package com.example.gstore_android.db_seeders

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class ProductSeeder() {


    // AI has been used to generate the data

    private val defaultUrl : String = "https://firebasestorage.googleapis.com/v0/b/gstore-final.firebasestorage.app/o/Products%2Fdefault.jpg?alt=media&token=c3b32558-96c9-46f0-a69b-91ae465be36c"

    private val products = listOf(
        mapOf(
            "name" to "Apple",
            "category" to "Fruits",
            "price" to 2.99,
            "discount" to 5,
            "quantity" to 50,
            "brand" to "Fresh Farm",
            "imageUrl" to "https://firebasestorage.googleapis.com/v0/b/gstore-final.firebasestorage.app/o/Products%2FFruits%2Fapple.jpg?alt=media&token=4a2120b7-efae-4ff2-841a-a0b8fec040ab"
        ),
        mapOf(
            "name" to "Banana",
            "category" to "Fruits",
            "price" to 1.29,
            "discount" to 10,
            "quantity" to 100,
            "brand" to "Tropica",
            "imageUrl" to "https://firebasestorage.googleapis.com/v0/b/gstore-final.firebasestorage.app/o/Products%2FFruits%2Fbanana.jpg?alt=media&token=010b6e9b-17ba-40ea-a913-723d72bef363"
        ),
        mapOf(
            "name" to "Mango",
            "category" to "Fruits",
            "price" to 3.49,
            "discount" to 15,
            "quantity" to 30,
            "brand" to "Golden Orchard",
            "imageUrl" to defaultUrl
        ),

        // Vegetables
        mapOf(
            "name" to "Carrot",
            "category" to "Vegetables",
            "price" to 1.49,
            "discount" to 5,
            "quantity" to 80,
            "brand" to "Green Leaf",
            "imageUrl" to defaultUrl
        ),
        mapOf(
            "name" to "Broccoli",
            "category" to "Vegetables",
            "price" to 2.79,
            "discount" to 8,
            "quantity" to 60,
            "brand" to "Farm Fresh",
            "imageUrl" to defaultUrl
        ),
        mapOf(
            "name" to "Tomato",
            "category" to "Vegetables",
            "price" to 1.99,
            "discount" to 12,
            "quantity" to 90,
            "brand" to "Nature’s Basket",
            "imageUrl" to defaultUrl
        ),

        // Dairy
        mapOf(
            "name" to "Whole Milk",
            "category" to "Dairy",
            "price" to 3.59,
            "discount" to 5,
            "quantity" to 40,
            "brand" to "Dairyland",
            "imageUrl" to defaultUrl
        ),
        mapOf(
            "name" to "Cheddar Cheese",
            "category" to "Dairy",
            "price" to 4.99,
            "discount" to 10,
            "quantity" to 25,
            "brand" to "Cheese Co.",
            "imageUrl" to defaultUrl
        ),
        mapOf(
            "name" to "Butter",
            "category" to "Dairy",
            "price" to 2.49,
            "discount" to 7,
            "quantity" to 35,
            "brand" to "Creamy Delight",
            "imageUrl" to defaultUrl
        ),

        // Cereals
        mapOf(
            "name" to "Oats",
            "category" to "Cereals",
            "price" to 3.99,
            "discount" to 5,
            "quantity" to 50,
            "brand" to "Healthy Start",
            "imageUrl" to defaultUrl
        ),
        mapOf(
            "name" to "Cornflakes",
            "category" to "Cereals",
            "price" to 2.99,
            "discount" to 8,
            "quantity" to 60,
            "brand" to "Golden Flakes",
            "imageUrl" to defaultUrl
        ),
        mapOf(
            "name" to "Muesli",
            "category" to "Cereals",
            "price" to 5.49,
            "discount" to 10,
            "quantity" to 40,
            "brand" to "NutriBite",
            "imageUrl" to defaultUrl
        )
    )

    fun fireStoreSeeder(firestore: FirebaseFirestore){


        this.products.forEach { product -> {

            firestore.collection("products").document().set(product).addOnSuccessListener {

                Log.d("SEEDER_SUCCESS", "Seeded")
            }.addOnFailureListener {

                Log.d("SEEDER_FAILURE", "Seeded")
            }

        } }




    }


}

