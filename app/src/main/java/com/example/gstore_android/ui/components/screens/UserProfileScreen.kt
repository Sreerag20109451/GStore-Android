package com.example.gstore_android.ui.components.screens

import android.net.Uri
import android.util.Log
import android.widget.Spinner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.example.gstore_android.ui.components.contact.ContactUsScreen
import com.example.gstore_android.viewmodels.AuthViewModel

@Composable
fun UserProfileScreen(authViewModel: AuthViewModel) {

    val defaultUrl: String = "https://firebasestorage.googleapis.com/v0/b/gstore-final.firebasestorage.app/o/Products%2Fdefault.jpg?alt=media&token=c3b32558-96c9-46f0-a69b-91ae465be36c"
    var user = authViewModel.userSignedIn.value
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var imgLoading = authViewModel.imageIsLoading.value
    val context = LocalContext.current
    var photouriState = authViewModel.uploadedPhotoUri.value
    // Launcher to open gallery
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        selectedImageUri = uri
        authViewModel.uploadPicandGetPic(selectedImageUri!!)

    }

    Log.d("PHOTOUTI", "${user?.photouri}")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth().fillMaxHeight() // Make the column fill the width
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally, // Centering horizontally
                verticalArrangement = Arrangement.Center // Centering vertically
            ) {

                if(imgLoading) {
                    CircularProgressIndicator()
                }
                else{
                    Image(
                        painter = rememberAsyncImagePainter(model = photouriState ?: user?.photouri ?: defaultUrl),
                        contentDescription = "User Profile Image",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(8.dp)
                            .clip(CircleShape)
                    )

                }

                // Change Image Icon
                IconButton(
                    onClick = { launcher.launch("image/*") }, // Open the gallery
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Change Profile Image",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // User Name
                Text(
                    text = user?.name ?: "No Name",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                // User Email
                Text(
                    text = user?.email ?: "No Email",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }


        // Add the ContactUsScreen as the last item
        item {
            Spacer(modifier = Modifier.height(190.dp))

            // Contact Us Section
            ContactUsScreen()
        }
    }
}
