package com.example.gstore_android.ui.components.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SignupScreen(){

    val focus = LocalFocusManager.current

    var name by remember { mutableStateOf(" ") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.padding(24.dp).background(color = Color.Magenta), contentAlignment = Alignment.Center )  {
        Column(modifier = Modifier.background(color = Color.DarkGray).padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.Center,)  {

            OutlinedTextField(value = name, onValueChange = {name = it }, modifier = Modifier.padding(4.dp), label = {Text("Name")}, )
            OutlinedTextField(value = email, onValueChange = {email = it }, modifier = Modifier.padding(4.dp), label = {Text("Email")}, )
            OutlinedTextField(value = password, onValueChange = {password = it }, modifier = Modifier.padding(4.dp), label = {Text("Password")}, )
            Button(onClick = {}) {Text("Sign up here") }
            HorizontalDivider(modifier = Modifier.padding(10.dp), thickness = 4.dp )
            Text(text =  "Do you already have an account, login here", fontSize = 10.sp, fontFamily = FontFamily.SansSerif)

        }
    }



}