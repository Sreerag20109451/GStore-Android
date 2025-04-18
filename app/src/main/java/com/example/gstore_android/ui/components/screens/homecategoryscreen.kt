package com.example.gstore_android.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gstore_android.data.models.User
import com.example.gstore_android.ui.components.products.CategoryCard
import com.example.gstore_android.ui.theme.ThemeManager


@Composable
fun HomeAndCategoryScreen(user: User, themeManager: ThemeManager){

    val colors = MaterialTheme.colorScheme

    Column(modifier = Modifier.fillMaxWidth().padding(top = 40.dp, start = 8.dp).background(colors.background), verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.Start) {

        Text(text = "Welcome, ${user.name}" , fontWeight = FontWeight.Bold, fontFamily = FontFamily.Cursive, fontSize = 40.sp, color = colors.tertiary, modifier = Modifier.padding(20.dp))
        Spacer(modifier = Modifier.size(30.dp))
        Text(text = "Browse Categories" , fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, fontSize = 20.sp, color = colors.tertiary, modifier = Modifier.padding(10.dp))
        Spacer(modifier = Modifier.size(10.dp))
        HorizontalDivider(modifier = Modifier.padding(start = 10.dp, end = 10.dp))
        Spacer(modifier = Modifier.size(40.dp))

        Column (modifier = Modifier.fillMaxWidth().padding(8.dp).background(colors.background), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Row(horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically ) {

                CategoryCard(categoryName = "Fruits", cardColor = colors.secondary)
                CategoryCard(categoryName = "Vegetables", cardColor = colors.secondary)
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically ) {

                CategoryCard(categoryName = "Dairy", cardColor = colors.secondary)
                CategoryCard(categoryName = "Cereals", cardColor = colors.secondary)
            }
        }



    }

}

