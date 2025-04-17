package com.example.gstore_android.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gstore_android.navigation.AuthNavigation
import com.example.gstore_android.ui.components.popups.CustomProgressBar
import com.example.gstore_android.viewmodels.AuthViewModel
import com.example.gstore_android.viewmodels.ScreenViewModel

@Composable
 fun MainScreen(modifier: Modifier, authViewModel : AuthViewModel = hiltViewModel<AuthViewModel>(),
                screenViewModel: ScreenViewModel = hiltViewModel<ScreenViewModel>()) {

     var signedInUser = authViewModel.userSignedIn.value
    var isLoading = authViewModel.isLoading.value

//    Log.d("SESSION_USER", " The user is ${signedInUser?.name : "shbdj"} --- ")


    Box(modifier = Modifier.fillMaxSize()){

        if(isLoading){

            CustomProgressBar("It is loading")
        }

        if(signedInUser!=null){

            HomeScreen(authViewModel = authViewModel, screenViewModel = screenViewModel)

        }
        if(!isLoading && signedInUser==null){

            AuthNavigation()
        }



    }




    }


@Composable
fun NotificationMessage(vm : AuthViewModel = hiltViewModel<AuthViewModel>()){

    val context = LocalContext.current
    val message by vm.popUpmessage

    Log.d("POP_UP_NOTIF", "${vm.popUpmessage.value}")

    LaunchedEffect(message) {
        message?.let {
            Log.d("TOAST_DEBUG", "Showing toast: $it")
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            // Clear after showing
            vm.popUpmessage.value = null

        }
    }

}
// KEY FIX: Use 'by' to properly observe state

