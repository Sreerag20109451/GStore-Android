package com.example.gstore_android.ui.components.auth

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun LoginScreen(navigateToSignup: () -> Unit, authVM: AuthViewModel) {
    val context = LocalContext.current
    val colors = MaterialTheme.colorScheme
    NotificationMessage(authVM)

    Box(
        modifier = Modifier.background(color = colors.background).fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val focus = LocalFocusManager.current
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.background(color = colors.background).fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().height(280.dp)
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
                    color = colors.onBackground,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Cursive
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
                    .background(color = colors.background, shape = RoundedCornerShape(20.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.padding(4.dp).fillMaxWidth(),
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Default.Email, null) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colors.primary,
                        unfocusedContainerColor = colors.onBackground.copy(alpha = 0.2f)
                    )
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.padding(4.dp).fillMaxWidth(),
                    label = { Text("Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, null) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colors.primary,
                        unfocusedContainerColor = colors.onBackground.copy(alpha = 0.2f)
                    )
                )
            }
            Column(
                modifier = Modifier.background(color = colors.background).padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        focus.clearFocus(force = true)
                        authVM.loginUser(email, password)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary,
                        contentColor = colors.onPrimary
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50)
                ) { Text("Sign In", color = colors.onPrimary) }
                Button(
                    onClick = {
                        focus.clearFocus(force = true)
                        authVM.googleSignUp(context as Activity)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.secondary,
                        contentColor = colors.onSecondary
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.glogo_foreground),
                            contentDescription = "g",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text("Sign In with Google", color = colors.onSecondary)
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(10.dp), thickness = 0.dp)

                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp).height(280.dp)
                        .background(color = colors.background, shape = RoundedCornerShape(20.dp))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = "Do not have an account, Sign Up here",
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.clickable { navigateToSignup() },
                        color = colors.primary
                    )
                }
            }
        }
    }
}
