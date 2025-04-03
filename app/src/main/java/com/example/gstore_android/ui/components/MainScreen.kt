package com.example.gstore_android.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gstore_android.ui.components.auth.SignupScreen
import com.example.gstore_android.viewmodels.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MainScreen(modifier: Modifier , authViewModel : AuthViewModel = hiltViewModel<AuthViewModel>()){

    var currentUser = authViewModel.currentUser

    if(currentUser==null){
        SignupScreen()
    }
    else{
        Text("Yet to be implemented")

    }


}

