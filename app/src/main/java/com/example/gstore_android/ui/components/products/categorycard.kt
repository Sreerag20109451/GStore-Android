package com.example.gstore_android.ui.components.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryCard(
    categoryName: String,
) {
    Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {

        Text(text=categoryName, fontSize = 20.sp, fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(20.dp))
        LazyRow {  }




    }
}