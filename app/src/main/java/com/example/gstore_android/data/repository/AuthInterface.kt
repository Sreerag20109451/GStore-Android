package com.example.gstore_android.data.repository

interface AuthInterface {

    suspend fun signUpUser(name : String, email : String, password : String) : Boolean
    suspend fun emailAlreadyExists(email: String) : Boolean
}