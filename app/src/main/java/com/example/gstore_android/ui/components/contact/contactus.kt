package com.example.gstore_android.ui.components.contact

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun ContactUsScreen() {
    // State for complaint text
    var complaintText by remember { mutableStateOf(TextFieldValue()) }
    var submittedComplaint by remember { mutableStateOf(false) }


    // List of FAQs
    val faqs = listOf(
        "Q: How do I track my order?\nA: You can track your order by logging into your account and checking the order history.",
        "Q: How do I cancel my order?\nA: You can cancel your order within 24 hours from the time of purchase by visiting your order details page.",
        "Q: What is the return policy?\nA: Returns are accepted within 30 days if the product is unused and in its original packaging."
    )


    fun resetSub(){

        submittedComplaint =false
    }
    if(submittedComplaint){
        ComplaintSentDialog(onDismiss =  { resetSub()})
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Contact Us",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Raise a Complaint Section
        Text(
            text = "Raise a Complaint:",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Text field for complaints
        BasicTextField(
            value = complaintText,
            onValueChange = { complaintText = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium),
            singleLine = false
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Submit Complaint Button
        Button(
            onClick = {
              submittedComplaint =true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Complaint")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // FAQs Section
        Text(
            text = "Frequently Asked Questions (FAQs)",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(10.dp))

        // List of FAQs
        faqs.forEach { faq ->
            Text(
                text = faq,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Contact Email Section
        Text(
            text = "For further inquiries, contact us at:",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Displaying the email
        Text(
            text = "20109451@wit.ie",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Blue,
            modifier = Modifier.clickable {
                // Handle the email click (e.g., open email app)
            }
        )
    }
}

@Composable
fun ComplaintSentDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Complaint Sent")
        },
        text = {
            Text(text = "Thank you for submitting your complaint. We will get back to you shortly.")
        },
        confirmButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Ok")
            }
        }
    )
}