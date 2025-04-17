package com.example.gstore_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gstore_android.data.models.User
import com.example.gstore_android.ui.theme.PurpleGrey40
import com.example.gstore_android.ui.theme.accentColor
import com.example.gstore_android.ui.theme.backgroundColor
import com.example.gstore_android.ui.theme.scaffoldbg
import com.example.gstore_android.ui.theme.secondaryColor
import com.example.gstore_android.viewmodels.AuthViewModel
import com.example.gstore_android.viewmodels.ScreenViewModel
import java.nio.file.WatchEvent

@Composable
fun HomeScreen (authViewModel: AuthViewModel, screenViewModel: ScreenViewModel){


    var isCategoryOpen = screenViewModel.isCategoryOpen.value
    var isOrdersOpen  = screenViewModel.isOrdersOpen.value
    var isProfileOpen =  screenViewModel.isProfileOpen.value
    var isSearchOpen  = screenViewModel.isSearchOpen.value


    val userdata = authViewModel.userSignedIn.value
    Scaffold(modifier = Modifier.fillMaxSize().background(scaffoldbg), topBar = {TopAppBarView()} , bottomBar = { BottomAppView(screenViewModel) }) { innerPadding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(scaffoldbg) ) {

            if(isCategoryOpen){
                HomeAndCategoryScreen(userdata!!)

            }



        }



    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(){

    TopAppBar(title = { Text("Gstore", color = accentColor) } ,   colors = TopAppBarDefaults.topAppBarColors(
        containerColor = backgroundColor, // this sets the background color
        titleContentColor = accentColor,  // optional: color of the title
        actionIconContentColor = Color.Black // optional: icon color
    ), actions = {
        IconButton(onClick = {} ) {
            Icon(imageVector = Icons.Default.ShoppingCart , tint = Color.Black, contentDescription = "Go to Cart")
        }

    } )

}

@Composable
fun BottomAppView(screenViewModel: ScreenViewModel) {
    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp).background(backgroundColor), horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically, )  {

        IconButton(modifier = Modifier.padding(bottom = 30.dp , top = 10.dp, start = 8.dp), onClick = {

        } ) {
            Icon(modifier = Modifier.size(60.dp), imageVector = Icons.Default.Home , tint = accentColor, contentDescription = "Go to Home")
        }

        IconButton(modifier = Modifier.padding(bottom = 30.dp , top = 10.dp), onClick = {} ) {
            Icon(modifier = Modifier.size(60.dp), imageVector = Icons.Default.Menu , tint = accentColor, contentDescription = "Go to Home")
        }


        IconButton(modifier = Modifier.padding(bottom = 30.dp, top = 10.dp), onClick = {} ) {
            Icon(modifier = Modifier.size(60.dp), imageVector = Icons.Default.Search , tint = accentColor, contentDescription = "Search")
        }

        IconButton(onClick = {} , Modifier.padding(bottom = 30.dp, top = 10.dp, end = 8.dp),) {
            Icon(modifier = Modifier.size(60.dp),imageVector = Icons.Default.AccountBox , tint = accentColor, contentDescription = "Go to profile")

        }


    }
}
