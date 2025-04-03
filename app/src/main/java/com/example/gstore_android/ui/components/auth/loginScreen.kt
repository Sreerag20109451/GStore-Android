package com.example.gstore_android.ui.components.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gstore_android.R


@Composable
fun LoginScreen(navigateToSignup: () -> Unit) {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Box(modifier = Modifier.background(color = Color.DarkGray).fillMaxSize(), contentAlignment = Alignment.Center )  {
        Column(modifier = Modifier.background(color = Color.DarkGray).padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.SpaceBetween)  {
            Row ( horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "g", modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.size(8.dp))
                Text("Welcome to GStores", color = Color.White)
            }
            OutlinedTextField(value = email, onValueChange = {email = it }, modifier = Modifier.padding(4.dp),
                label = {   Icon(imageVector = Icons.Default.Email,
                    contentDescription = "icon")}, placeholder = {Text("Email")} )
            OutlinedTextField(value = password, onValueChange = {password = it }, modifier = Modifier.padding(4.dp),   label = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "icon")},
                placeholder = {Text("Password")}  )
            Spacer(modifier = Modifier.size(30.dp))
            Button(onClick = {} , colors = ButtonDefaults.buttonColors(Color.Blue)) { Text("Login", color = Color.White )}
            Spacer(modifier = Modifier.size(30.dp))
            Button(onClick = {}, colors = ButtonDefaults.buttonColors(Color.Blue) ) {

                Row ( horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                    Image(painter = painterResource(R.drawable.glogo_foreground), contentDescription = "g", modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text("Sign in with Google", color = Color.White)
                }
            }
            HorizontalDivider(modifier = Modifier.padding(10.dp), thickness = 4.dp )
            Text(text =  "Do not have an account? Sign up here", fontSize = 14.sp, fontFamily = FontFamily.SansSerif , modifier = Modifier.clickable{ navigateToSignup()})




        }
    }


}