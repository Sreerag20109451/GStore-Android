package com.example.gstore_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.gstore_android.ui.components.MainScreen
import com.example.gstore_android.ui.theme.GStoreAndroidTheme
import com.example.gstore_android.ui.theme.ThemeManager
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GStoreAndroidTheme {


              Scaffold { innerpadding ->

                  MainScreen(modifier = Modifier.padding(innerpadding))
              }

            }
        }
    }
}



@Composable
fun FirstScreen(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(), // Fill the screen size
    ) {
        Text(
            text = "Hello",
            modifier = Modifier.align(Alignment.Center), // Align text to the center
            color = Color.White, // Ensure text is visible on black background
        )
    }
}