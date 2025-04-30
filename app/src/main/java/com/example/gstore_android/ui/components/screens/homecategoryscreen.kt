package com.example.gstore_android.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gstore_android.data.models.Category
import com.example.gstore_android.data.models.User
import com.example.gstore_android.ui.components.popups.CustomProgressBar
import com.example.gstore_android.ui.components.products.CategoryCard
import com.example.gstore_android.ui.theme.ThemeManager
import com.example.gstore_android.viewmodels.ProductsViewModel


@Composable
fun HomeAndCategoryScreen(user: User, themeManager: ThemeManager, productsViewModel : ProductsViewModel = hiltViewModel<ProductsViewModel>()){

    val colors = MaterialTheme.colorScheme
    val isLoading by productsViewModel.isLoading


    Column(modifier = Modifier.fillMaxWidth().padding( start = 8.dp).background(colors.background), verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.Start) {

        Text(text = "Welcome, ${user.name}" , fontWeight = FontWeight.Bold, fontFamily = FontFamily.Cursive, fontSize = 40.sp, color = colors.tertiary , modifier = Modifier.padding(10.dp))
        Text(text = "Browse Categories" , fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, fontSize = 20.sp, color = colors.tertiary, modifier = Modifier.padding(10.dp))
        HorizontalDivider(modifier = Modifier.padding(start = 10.dp, end = 10.dp))



        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item { CategoryCard(categoryName = Category.Fruits) }
            item { CategoryCard(categoryName = Category.Vegetables) }
            item { CategoryCard(categoryName = Category.Dairy) }
            item { CategoryCard(categoryName = Category.Cereals) }
        }


    }

}

