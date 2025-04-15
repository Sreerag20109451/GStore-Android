package com.example.gstore_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen (Screen : @Composable ((modifier : Modifier) -> Unit)){
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {TopAppBarView()} , bottomBar = { BottomAppView() }) { innerPadding ->
        Screen(Modifier.padding(innerPadding))

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(){

    TopAppBar(title = { Text("Gstore", color = Color.Red) } , modifier =Modifier.padding(start = 8.dp, end = 8.dp).background(color = Color.Blue), actions = {
        IconButton(onClick = {} ) {
            Icon(imageVector = Icons.Default.ShoppingCart , tint = Color.White, contentDescription = "Go to Cart")
        }

    } )

}

@Composable
fun BottomAppView() {
    Row(modifier = Modifier.fillMaxWidth().background(Color.Blue).padding(34.dp) , horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically)  {

        IconButton(onClick = {} ) {
            Icon(imageVector = Icons.Default.Home , tint = Color.White, contentDescription = "Go to Home")
        }
        IconButton(onClick = {} ) {
            Icon(imageVector = Icons.Default.Search , tint = Color.White, contentDescription = "Search")
        }
        IconButton(onClick = {} ) {
            Icon(imageVector = Icons.Default.Person , tint = Color.White, contentDescription = "Go to profile")

        }

    }
}
