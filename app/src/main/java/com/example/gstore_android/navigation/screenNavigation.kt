//package com.example.gstore_android.navigation
//
//
//import androidx.compose.runtime.Composable
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.gstore_android.ui.components.auth.LoginScreen
//import com.example.gstore_android.ui.components.auth.SignupScreen
//import com.example.gstore_android.viewmodels.AuthViewModel
//import kotlinx.serialization.Serializable
//
//
//@Serializable
//object MainScreen
//
//@Serializable
//object CartScreen
//
//
//@Composable
//fun AuthNavigation(){
//
//    val navController  = rememberNavController()
//
//    NavHost(navController = navController, startDestination = SignupScreen){
//        composable <SignupScreen>{
//            SignupScreen({
//                navController.navigate(LoginScreen)
//
//            }, authVM = hiltViewModel<AuthViewModel>())
//
//        }
//
//        composable <LoginScreen>{
//            LoginScreen( {
//                navController.navigate(SignupScreen)
//            }, authVM = hiltViewModel<AuthViewModel>())
//
//        }
//    }
//
//}
