package com.example.gstore_android.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gstore_android.navigation.AuthNavigation
import com.example.gstore_android.ui.components.auth.SignupScreen
import com.example.gstore_android.ui.components.popups.PopUpAuthError
import com.example.gstore_android.viewmodels.AuthViewModel
import kotlinx.coroutines.delay

@Composable
 fun MainScreen(modifier: Modifier, authViewModel : AuthViewModel = hiltViewModel<AuthViewModel>()) {

     var currentUser = authViewModel.currentUser.value
     NotificationMessage(authViewModel)

    Log.d("SESSION_USER-m", " The user is ${currentUser} --- ")

    AuthNavigation()
//    if(currentUser!=null){
//
//        Text("Yet to be implemented")
//    }
//    else{
//
//        AuthNavigation()
//    }




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

