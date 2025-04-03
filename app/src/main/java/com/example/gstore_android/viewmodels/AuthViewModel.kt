package com.example.gstore_android.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(var auth : FirebaseAuth, var firestore : FirebaseFirestore ) : ViewModel(){


    public var currentUser = auth.currentUser?.uid
    var isLoading  =   mutableStateOf(false)
    var isError = mutableStateOf(false)
    var popUpmessage = mutableStateOf<String?>(null)


    fun signUpUser( name :  String, email : String , password : String){



    }





    private fun handleException(exception: Exception? = null, message : String){

        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val dismessage = if(message.isEmpty()) errorMsg else "$message $errorMsg"
        popUpmessage.value = dismessage

    }



}