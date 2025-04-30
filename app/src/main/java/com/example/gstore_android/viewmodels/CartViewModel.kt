package com.example.gstore_android.viewmodels

import androidx.lifecycle.ViewModel
import com.example.gstore_android.data.repository.AuthRepositoryImpl
import com.example.gstore_android.data.repository.OrderRepoImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(val firestore: FirebaseFirestore, val orderRepoImpl: OrderRepoImpl): ViewModel(){




    fun addToCart(product){


    }

}