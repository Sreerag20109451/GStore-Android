package com.example.gstore_android.ui.components.auth

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gstore_android.R
import com.example.gstore_android.ui.components.NotificationMessage
import com.example.gstore_android.viewmodels.AuthViewModel

@Composable
fun SignupScreen(navigateToLogin: () -> Unit, authVM: AuthViewModel) {
    val colors = MaterialTheme.colorScheme
    val focus = LocalFocusManager.current
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    NotificationMessage(authVM)

    Column(
        modifier = Modifier
            .background(color = colors.background)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(color = colors.secondary, shape = RoundedCornerShape(40.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "g",
                modifier = Modifier.size(60.dp),
                tint = colors.primary
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                "Welcome to GStore",
                color = Color.White,
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive
            )
        }

        // Input fields section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colors.background, shape = RoundedCornerShape(20.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.padding(4.dp).fillMaxWidth(),
                label = { Text("Name") },
                leadingIcon = { Icon(Icons.Default.Person, null) },

            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.padding(4.dp).fillMaxWidth(),
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, null) },

            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.padding(4.dp).fillMaxWidth(),
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, null) },
            )
        }


        Column(
            modifier = Modifier
                .background(color = colors.background)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    Log.d("BUTTON_TEST", "Button clicked") // Add this to verify clicks
                    focus.clearFocus(force = true)
                    authVM.signupUser(name, email, password)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.secondary,
                    contentColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50)
            ) { Text("Sign Up", color = Color.White) }

            Button(
                onClick = {
                    authVM.googleSignUp(context as Activity)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50)
            ) {
                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.glogo_foreground),
                        contentDescription = "g",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text("Sign up with Google", color = Color.White)
                }
            }

            // Divider for the section
            HorizontalDivider(modifier = Modifier.padding(10.dp), thickness = 0.dp)
        }

        // Login redirection section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .height(280.dp)
                .background(color = colors.background, shape = RoundedCornerShape(20.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Do you already have an account? Login here",
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.clickable { navigateToLogin() },
                color = colors.onBackground
            )
        }
    }
}
