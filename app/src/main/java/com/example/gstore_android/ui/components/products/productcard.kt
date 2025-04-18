package com.example.gstore_android.ui.components.products


import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.gstore_android.R
import com.example.gstore_android.data.models.Products
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun ProductCard(product: Products){

    var quantity = remember { mutableStateOf(1) }


    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
     val defaultUrl : String = "https://firebasestorage.googleapis.com/v0/b/gstore-final.firebasestorage.app/o/Products%2Fdefault.jpg?alt=media&token=c3b32558-96c9-46f0-a69b-91ae465be36c"

    val swipeOffset = remember { Animatable(0f) }

    val colorschema = MaterialTheme.colorScheme

    Box (modifier = Modifier
        .width(screenWidth * 0.8f)
        .padding(8.dp)
        .pointerInput(Unit) {
            coroutineScope {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        launch {
                            swipeOffset.animateTo(0f) // Optional: snap back on release
                        }
                    }
                ) { change, dragAmount ->
                    change.consume()
                    launch {
                        swipeOffset.snapTo(swipeOffset.value + dragAmount)
                    }
                }
            }
        }

    )
        {

            Card(modifier = Modifier.fillMaxWidth().background(colorschema.secondary),  shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)) {

                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){

                    Image(painter = rememberAsyncImagePainter(product.imageUrl, placeholder = painterResource(R.drawable.glogo_background)), contentDescription = product.name )
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {

                        Text(product.name, fontWeight = FontWeight.ExtraBold, fontSize = 40.sp, style = MaterialTheme.typography.titleMedium )
                        Spacer(modifier = Modifier.size(18.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            Text("€ ${product.price}", fontWeight = FontWeight.ExtraBold, fontSize = 40.sp, style = MaterialTheme.typography.titleSmall,)
                            Spacer(modifier = Modifier.size(18.dp))
                            Button(
                                onClick = { if (quantity.value > 1) quantity.value-- },
                                modifier = Modifier.size(36.dp).padding(8.dp)
                            ) {
                                Text("-", style = MaterialTheme.typography.titleLarge)
                            }

                            Text(
                                text = quantity.toString(),
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )

                            Button(
                                onClick = { quantity.value++ },
                                modifier = Modifier.size(36.dp).padding(8.dp)
                            ) {
                                Text("+", style = MaterialTheme.typography.titleLarge)
                            }
                        }
                    }

                }



            }
        }


}