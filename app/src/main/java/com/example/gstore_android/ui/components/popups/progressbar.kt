package com.example.gstore_android.ui.components.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gstore_android.ui.theme.backgroundColor

@Composable
fun CustomProgressBar(msg : String){


    Column(modifier = Modifier.fillMaxSize().background(color = backgroundColor), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {


        CircularProgressIndicator(modifier = Modifier.padding(10.dp).size(64.dp), color = Color.Cyan , strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth)
        Text(msg, fontSize = 20.sp , fontFamily = FontFamily.Serif , fontWeight = FontWeight.Bold)



    }


}