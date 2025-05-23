package com.example.gstore_android.ui.components

import android.annotation.SuppressLint
import android.content.pm.LauncherApps
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gstore_android.data.models.Category
import com.example.gstore_android.data.models.Order
import com.example.gstore_android.data.models.Product
import com.example.gstore_android.data.models.User
import com.example.gstore_android.ui.components.screens.AllProductsPage
import com.example.gstore_android.ui.components.screens.CartScreen
import com.example.gstore_android.ui.components.screens.HomeAndCategoryScreen
import com.example.gstore_android.ui.components.screens.OrdersScreen
import com.example.gstore_android.ui.components.screens.UserProfileScreen
import com.example.gstore_android.ui.theme.PurpleGrey40
import com.example.gstore_android.ui.theme.ThemeManager
import com.example.gstore_android.ui.theme.accentColor
import com.example.gstore_android.ui.theme.backgroundColor
import com.example.gstore_android.ui.theme.rememberThemeManager
import com.example.gstore_android.ui.theme.scaffoldbg
import com.example.gstore_android.ui.theme.secondaryColor
import com.example.gstore_android.viewmodels.AuthViewModel
import com.example.gstore_android.viewmodels.CartViewModel
import com.example.gstore_android.viewmodels.ScreenViewModel
import java.nio.file.WatchEvent
import java.util.Date

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen (authViewModel: AuthViewModel, screenViewModel: ScreenViewModel, themeManager: ThemeManager, cartViewModel: CartViewModel){


    var isCategoryOpen = screenViewModel.isCategoryOpen.value
    var isOrdersOpen  = screenViewModel.isOrdersOpen.value
    var isProfileOpen =  screenViewModel.isProfileOpen.value
    var isSearchOpen  = screenViewModel.isSearchOpen.value
    var darkTheme = mutableStateOf(isSystemInDarkTheme())
    var isCartOpen = screenViewModel.iscartOpen.value


    val userdata = authViewModel.userSignedIn.value
    Scaffold(modifier = Modifier.fillMaxSize().background(scaffoldbg), topBar = {TopAppBarView(themeManager = themeManager)} , bottomBar = { BottomAppView(screenViewModel, themeManager) }) { innerPadding ->
darkTheme
        val colors = MaterialTheme.colorScheme
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(colors.background) ) {

            if(isCategoryOpen){
                HomeAndCategoryScreen(userdata!!, themeManager = themeManager)

            }
            if(isSearchOpen){
                AllProductsPage()
            }

            if(isProfileOpen){
                UserProfileScreen(authViewModel)
            }
            if(isCartOpen){
                CartScreen(cartViewModel = cartViewModel)

            }
            if(isOrdersOpen){
                OrdersScreen()
            }


        }



    }
}




@SuppressLint("UnrememberedMutableState", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(themeManager: ThemeManager, screenViewModel : ScreenViewModel = hiltViewModel<ScreenViewModel>(), authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>()) {

    val darkTheme = themeManager.isDarkTheme
    var colors = MaterialTheme.colorScheme



        TopAppBar(
            title = {
                Text("Gstore", color = colors.primary)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colors.background,
                titleContentColor = colors.tertiary,
                actionIconContentColor = colors.secondary
            ),
            actions = {
                IconButton(onClick = {
                    themeManager.toggleTheme()  }) {
                    Icon(
                        imageVector = if (darkTheme.value) Icons.Filled.LightMode
                        else Icons.Filled.DarkMode,
                        contentDescription = "Toggle theme"
                    )
                }
                IconButton(onClick = {  screenViewModel.openCartScreen()}) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Go to Cart",
                        tint = colors.tertiary
                    )
                }
                Button(onClick = { authViewModel.logout()}) {
                    Text("Logout")
                }
            }
        )



}

@Composable
fun BottomAppView(screenViewModel: ScreenViewModel, themeManager: ThemeManager) {
    var isCategoryOpen = screenViewModel.isCategoryOpen.value
    var isOrdersOpen  = screenViewModel.isOrdersOpen.value
    var isProfileOpen =  screenViewModel.isProfileOpen.value
    var isSearchOpen  = screenViewModel.isSearchOpen.value


    val colors = MaterialTheme.colorScheme
    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp).background(colors.background), horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically, )  {

        IconButton(modifier = Modifier.padding(bottom = 30.dp , top = 10.dp, start = 8.dp),  onClick = {
            screenViewModel.openCategoryScreen()

        } ) {
            Icon(modifier = Modifier.size(60.dp), imageVector = Icons.Default.Home , tint = if (isCategoryOpen) colors.primary else colors.tertiary , contentDescription = "Go to Home")
        }

        IconButton(modifier = Modifier.padding(bottom = 30.dp , top = 10.dp), onClick = { screenViewModel.openSearchScreen()} ) {
            Icon(modifier = Modifier.size(60.dp), imageVector = Icons.Default.Search , tint = if (isSearchOpen) colors.primary else colors.tertiary , contentDescription = "Go to Home")
        }


        IconButton(modifier = Modifier.padding(bottom = 30.dp, top = 10.dp), onClick = {screenViewModel.openOrdersScreen()} ) {
            Icon(modifier = Modifier.size(60.dp), imageVector = Icons.Default.Menu , tint =  if (isOrdersOpen) colors.primary else colors.tertiary , contentDescription = "Search")
        }

        IconButton(onClick = {screenViewModel.openProfileScreen()} , Modifier.padding(bottom = 30.dp, top = 10.dp, end = 8.dp),) {
            Icon(modifier = Modifier.size(60.dp),imageVector = Icons.Default.AccountBox , tint =  if (isProfileOpen) colors.primary else colors.tertiary , contentDescription = "Go to profile")

        }


    }
}
