package com.example.gstore_android.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gstore_android.data.models.Event
import com.example.gstore_android.data.models.User
import com.example.gstore_android.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@HiltViewModel
class AuthViewModel @Inject constructor(var auth : FirebaseAuth, var firestore : FirebaseFirestore, val authRepo : AuthRepositoryImpl ) : ViewModel(){


     var currentUser = mutableStateOf(auth.currentUser?.uid)
    var isLoading  =   mutableStateOf(false)
    var popUpmessage = mutableStateOf<String?>(null)
    var userSignedIn : MutableState<User?> = mutableStateOf<User?>(null)

    init {

        val isUserFound = auth.currentUser
        if(isUserFound !=null){

            viewModelScope.launch {
               val userdata =  authRepo.getUserData(isUserFound.uid)
                userSignedIn.value = userdata
            }

        }

    }


    @OptIn(ExperimentalUuidApi::class)
    fun signupUser(name :  String, email : String, password : String) {
        Log.d("SESSION_USER", " The user is ${currentUser} --- ")
        if (name.isEmpty() or email.isEmpty() or password.isEmpty()) {

            popUpmessage.value = "Please fill in the fields"
            return

        }
        isLoading.value = true

        viewModelScope.launch {
            val userExists = authRepo.emailAlreadyExists(email)
            if(userExists) {
                popUpmessage.value = "Email Already Exists"
                isLoading.value = false
                return@launch
            }
            else{

                val userSignedIn = authRepo.signUpUser(name, email, password)
                if(userSignedIn){
                    popUpmessage.value = "User Signed In"
                    isLoading.value = false

                }
                else{

                    popUpmessage.value = "Signed up failed"
                    isLoading.value = false
                }

            }

        }





    }




    }








