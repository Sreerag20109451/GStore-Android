package com.example.gstore_android.ui.components.popups

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.gstore_android.viewmodels.AuthViewModel

@Composable
fun PopUpAuthError(message : String){

        Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()


}