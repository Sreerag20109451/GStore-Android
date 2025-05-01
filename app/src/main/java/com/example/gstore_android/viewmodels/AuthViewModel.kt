package com.example.gstore_android.viewmodels

import android.app.Activity
import android.net.Uri
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
import kotlinx.coroutines.time.delay
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi


@HiltViewModel
class AuthViewModel @Inject constructor(var auth : FirebaseAuth,
                                        val firestore : FirebaseFirestore,
                                        val authRepo : AuthRepositoryImpl ) : ViewModel(){


    var currentUserUid = mutableStateOf<String?>(null)
     var currentUser = mutableStateOf(auth.currentUser?.uid )
    var isLoading  =   mutableStateOf(false)
    var popUpmessage = mutableStateOf<String?>(null)
    var userSignedIn : MutableState<User?> = mutableStateOf<User?>(null)
    val uploadedPhotoUri = mutableStateOf<Uri?>(null)
    val imageIsLoading = mutableStateOf(false)


    init{

        Log.d("fefef", "$currentUser is ")

        isLoading.value = true
        if(currentUser.value!= null){
            viewModelScope.launch {
              try {
                  userSignedIn.value = authRepo.getUserData(currentUser.value!!)
                  Log.d("TFYTFYTR" , "GVHfh ${userSignedIn.value?.uid}")
                  if(userSignedIn.value!=null) currentUserUid.value = auth.currentUser?.uid
                  popUpmessage.value = "User data fetched"
                  isLoading.value = false
              }
              catch (exc : Exception){

                  popUpmessage.value = "Error fetching"
                  isLoading.value = false
              }

            }

        }
        else{
            isLoading.value = false
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
            val userExists = authRepo.emailAlreadyExists(email)
            if(userExists) {
                popUpmessage.value = "Email Already Exists"
                isLoading.value = false
                return@launch
            }
            else{
                val userSignedup = authRepo.signUpUser(name, email, password)
                if(userSignedup){
                    kotlinx.coroutines.delay(300)
                    currentUser.value = auth.currentUser?.uid // <<< Add this! // or better: wait until it's not null
                    userSignedIn.value = authRepo.getUserData(currentUser.value!!)
                    kotlinx.coroutines.delay(300)
                    Log.d("TFYTFYTR" , "GVHfh ${userSignedIn.value?.name}")
                    if(userSignedIn.value!=null) currentUserUid.value = auth.currentUser?.uid
                    Log.d("TFYTFYTR" , "GVHfh ${userSignedIn.value?.uid}")
                    popUpmessage.value = "User Signed In"
                    isLoading.value = false
                    return@launch

                }
                else{
                    popUpmessage.value = "Signed up failed"
                    isLoading.value = false
                    return@launch
                }

            }

        }





    }

    fun uploadPicandGetPic(imageUri: Uri) {
        viewModelScope.launch {
            imageIsLoading.value = true
            val photoUri = authRepo.UploadProfilePhoto(imageUri)
            uploadedPhotoUri.value = photoUri
            imageIsLoading.value = false
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
                kotlinx.coroutines.delay(300)
                userSignedIn.value = authRepo.getUserData(auth.currentUser?.uid!!)
                kotlinx.coroutines.delay(300)
                if(userSignedIn.value!=null) currentUserUid.value = auth.currentUser?.uid
                popUpmessage.value = "User logged In"
                return@launch
            }
            else{
                isLoading.value =false
                popUpmessage.value = "User login failed"
                return@launch
            }
        }
    }


    fun googleSignUp(context : Activity){

        isLoading.value = true
        viewModelScope.launch {

            Log.d("LOGIN_TEST1", "Works")
            val token = authRepo.googleSignup(context)
            if(token != null){
                kotlinx.coroutines.delay(300)
                userSignedIn.value = authRepo.getUserData(auth.currentUser?.uid!!)
                popUpmessage.value = "User data fetched"
                kotlinx.coroutines.delay(300)
                isLoading.value = false
                popUpmessage.value = "User Signed In"
                if(userSignedIn.value!=null) currentUserUid.value = auth.currentUser?.uid
                Log.d("JYfjfjhj" ,  "${currentUserUid.value}")
                Log.d("JYGDyfudyfaudyfufyuYSFYTY" , "GVHfh ${userSignedIn.value?.uid}")
                return@launch
            }
            else{
                isLoading.value =false
                popUpmessage.value = "User signup failed"
                return@launch
            }
        }


    }

    fun logout() {
        auth.signOut()
        popUpmessage.value ="Logged Out"
        userSignedIn.value =null
        currentUserUid.value = null
    }




    }








