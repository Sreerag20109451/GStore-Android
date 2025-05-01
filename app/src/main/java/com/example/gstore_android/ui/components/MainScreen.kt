package com.example.gstore_android.ui.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gstore_android.navigation.AuthNavigation
import com.example.gstore_android.ui.components.popups.CustomProgressBar
import com.example.gstore_android.ui.theme.ThemeManager
import com.example.gstore_android.ui.theme.rememberThemeManager
import com.example.gstore_android.viewmodels.AuthViewModel
import com.example.gstore_android.viewmodels.CartViewModel
import com.example.gstore_android.viewmodels.ScreenViewModel
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnrememberedMutableState")
@Composable
 fun MainScreen(modifier: Modifier, authViewModel :
                AuthViewModel = hiltViewModel<AuthViewModel>(),
                cartViewModel: CartViewModel =hiltViewModel<CartViewModel>(),
                screenViewModel: ScreenViewModel = hiltViewModel<ScreenViewModel>(), themeManager: ThemeManager = rememberThemeManager()
) {


     var auth = FirebaseAuth.getInstance()
     var userLoggedIn = remember { auth.currentUser?.uid }
    var signedInUser = authViewModel.userSignedIn.value
//    var userLoggedIn = authViewModel.currentUserUid.value
    var isLoading = authViewModel.isLoading.value

    var isDark = themeManager.isDarkTheme.value


    Log.d("JYGDUYWFDUTFY", "${signedInUser} jdbcshdbj ${userLoggedIn} ")

    MaterialTheme (
        colorScheme = if (isDark) darkColorScheme() else lightColorScheme()
    )
    {
        Box(modifier = Modifier.fillMaxSize()) {

            if (isLoading) {

                CustomProgressBar("It is loading")
            }

            if (signedInUser != null) {

                HomeScreen(authViewModel = authViewModel, screenViewModel = screenViewModel, themeManager = themeManager, cartViewModel = cartViewModel)

            }
            if (!isLoading && signedInUser == null) {

                AuthNavigation()
            }


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

