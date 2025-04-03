package com.example.gstore_android.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(var auth : FirebaseAuth, var firestore : FirebaseFirestore ) : ViewModel(){


    public var currentUser = auth.currentUser?.uid








}