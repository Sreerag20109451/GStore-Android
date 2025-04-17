package com.example.gstore_android.viewmodels

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gstore_android.data.models.User
import com.example.gstore_android.data.repository.AuthRepositoryImpl
import com.example.gstore_android.db_seeders.ProductSeeder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi


@HiltViewModel
class AuthViewModel @Inject constructor(var auth : FirebaseAuth,
                                        val firestore : FirebaseFirestore,
                                        val authRepo : AuthRepositoryImpl ) : ViewModel(){


     var currentUser = mutableStateOf(auth.currentUser?.uid)
    var isLoading  =   mutableStateOf(false)
    var popUpmessage = mutableStateOf<String?>(null)
    var userSignedIn : MutableState<User?> = mutableStateOf<User?>(null)
    var seeder = ProductSeeder()

    init{

//        seeder.fireStoreSeeder(firestore)
        isLoading.value = true
        if(currentUser.value!= null){
            viewModelScope.launch {

                userSignedIn.value = authRepo.getUserData(currentUser.value!!)
                popUpmessage.value = "User data fetched"
                isLoading.value = false

            }

        }
    }


    @OptIn(ExperimentalUuidApi::class)
    fun signupUser(name :  String, email : String, password : String) {
//        Log.d("SESSION_USER", " The user is ${currentUser} --- ")
        if (name.isEmpty() or email.isEmpty() or password.isEmpty()) {

            popUpmessage.value = "Please fill in the fields"
            return

        }
        isLoading.value = true

        viewModelScope.launch {
            Log.d("BEFORCHECK", "email check starts")
            val userExists = authRepo.emailAlreadyExists(email)
            Log.d("AFTERCHECKEAMAILFINISH", "email check finished ,${userExists} is this")
            if(userExists) {
                popUpmessage.value = "Email Already Exists"
                isLoading.value = false
                return@launch
            }
            else{
                Log.d("BEFORESIGN", "signin starts")
                val userSignedIn = authRepo.signUpUser(name, email, password)
                Log.d("SIGNINEND", "in-repo")
                if(userSignedIn){
                    Log.d("AFTER", "signned")
                    popUpmessage.value = "User Signed In"
                    isLoading.value = false
                    return@launch

                }
                else{

                    Log.d("AFTERSIGNFAILED", "failed sign in")


                    popUpmessage.value = "Signed up failed"
                    isLoading.value = false
                    return@launch
                }

            }

        }





    }

    fun loginUser(email: String, password: String){

        if ( email.isEmpty() or password.isEmpty()) {

            popUpmessage.value = "Please fill in the fields"
            return

        }
        isLoading.value = true
        viewModelScope.launch {

            Log.d("LOGIN_TEST", "Motherfucker does work")
            val token = authRepo.loginUser(email, password)
            if(token != null){
                isLoading.value = false
                popUpmessage.value = "User logged In"

                Log.d("LOGIN_WORKed", "Motherfucker does work, ${token}")
                return@launch
            }
            else{
                Log.d("FUCK_DIDNT_WORK", "fuckkkkkkkk")
                isLoading.value =false
                popUpmessage.value = "User login failed"
                return@launch
            }
        }
    }


    fun googleSignUp(context : Activity){

        isLoading.value = true
        viewModelScope.launch {

            Log.d("LOGIN_TEST1", "Motherfucker does work")
            val token = authRepo.googleSignup(context)
            if(token != null){
                isLoading.value = false
                popUpmessage.value = "User Signed In"

                Log.d("LOGIN_WORKedg", "Motherfucker does work, ${token}")
                return@launch
            }
            else{
                Log.d("FUCK_DIDNT_WORKg", "fuckkkkkkkk")
                isLoading.value =false
                popUpmessage.value = "User signup failed"
                return@launch
            }
        }


    }




    }








