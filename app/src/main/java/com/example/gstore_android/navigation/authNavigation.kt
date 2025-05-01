package com.example.gstore_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gstore_android.ui.components.MainScreen
import com.example.gstore_android.ui.components.auth.LoginScreen
import com.example.gstore_android.ui.components.auth.SignupScreen
import com.example.gstore_android.viewmodels.AuthViewModel
import com.example.gstore_android.viewmodels.CartViewModel
import com.example.gstore_android.viewmodels.ScreenViewModel
import kotlinx.serialization.Serializable


@Serializable
object SignupScreen

@Serializable
object LoginScreen




@Composable
fun AuthNavigation(
    authVM: AuthViewModel = hiltViewModel(),
    screenViewModel: ScreenViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val userSignedIn = authVM.userSignedIn

    // Observe and react to login state change
    LaunchedEffect (userSignedIn.value) {
        if (userSignedIn.value != null) {
            navController.navigate("main") {
                popUpTo("signup") { inclusive = true } // Remove auth from backstack
            }
        }
    }

    NavHost(navController = navController, startDestination = "signup") {
        composable("signup") {
            SignupScreen({
                navController.navigate("login")
            }, authVM = authVM)
        }

        composable("login") {
            LoginScreen({
                navController.navigate("signup")
            }, authVM = authVM)
        }

        composable("main") {
            MainScreen(
                modifier = Modifier,
                authViewModel = authVM,
                screenViewModel = screenViewModel,
                cartViewModel = cartViewModel
            )
        }
    }
}
