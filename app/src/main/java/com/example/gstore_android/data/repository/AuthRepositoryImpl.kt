package com.example.gstore_android.data.repository

import android.util.Log
import com.example.gstore_android.data.dao.AuthDAO
import com.example.gstore_android.data.dao.AuthDAOImpl
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val auth : FirebaseAuth , val authDAOImpl : AuthDAOImpl)  : AuthInterface {

    override suspend fun signUpUser(name : String, email : String, password: String) : Boolean{

        val isSignedIn = authDAOImpl.signUpUser(name, email,password)
        if(isSignedIn) {
           return authDAOImpl.createUser(name, email, password)
        }

        return false


    }

    override suspend fun emailAlreadyExists(email: String): Boolean {
        if(authDAOImpl.checkIfEmailExisrts(email)) return true
        return false
    }




}