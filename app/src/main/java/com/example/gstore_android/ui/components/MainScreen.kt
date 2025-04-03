package com.example.gstore_android.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gstore_android.navigation.AuthNavigation
import com.example.gstore_android.ui.components.auth.SignupScreen
import com.example.gstore_android.viewmodels.AuthViewModel

@Composable
fun MainScreen(modifier: Modifier , authViewModel : AuthViewModel = hiltViewModel<AuthViewModel>()){

    var currentUser = authViewModel.currentUser

    if(currentUser==null){

        AuthNavigation()
    }
    else{
        Text("Yet to be implemented")

    }


}

