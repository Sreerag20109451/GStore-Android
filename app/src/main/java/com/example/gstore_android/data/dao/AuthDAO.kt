package com.example.gstore_android.data.dao

import com.example.gstore_android.data.models.User

interface AuthDAO {

    suspend fun createUser(name : String, email : String, password : String?, photouri : String?) : Boolean
    suspend fun  getUser(uid : String) :  User?
    suspend fun signUpUser(name : String, email : String, password : String) : Boolean
    suspend fun checkIfEmailExisrts(email : String) : Boolean
    suspend fun loginUser(email: String, password: String) : String?
    suspend fun firebaseAuthWithGoogle(idToken : String) : String?
    suspend fun getProfilePhoto() : Boolean

}