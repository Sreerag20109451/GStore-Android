package com.example.gstore_android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gstore_android.ui.components.auth.LoginScreen
import com.example.gstore_android.ui.components.auth.SignupScreen
import kotlinx.serialization.Serializable


@Serializable
object SignupScreen

@Serializable
object LoginScreen


@Composable
fun AuthNavigation(){

    val navController  = rememberNavController()

    NavHost(navController = navController, startDestination = SignupScreen){
        composable <SignupScreen>{
            SignupScreen() {
                navController.navigate(LoginScreen)
            }

        }

        composable <LoginScreen>{
            LoginScreen() {
                navController.navigate(SignupScreen)
            }

        }
    }

}
