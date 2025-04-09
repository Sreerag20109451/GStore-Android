package com.example.gstore_android.data.repository

import com.example.gstore_android.data.models.User

interface AuthInterface {

    suspend fun signUpUser(name : String, email : String, password : String) : Boolean
    suspend fun emailAlreadyExists(email: String) : Boolean
    suspend fun  getUserData(uid : String) : User?
}