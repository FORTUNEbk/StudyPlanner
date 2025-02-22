package com.example.studyplanner.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign

@Composable
fun NewPlanScreen() {
    var planName by remember { mutableStateOf("") }
    var planColor by remember { mutableStateOf(Color.Gray) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("New Plan", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        OutlinedTextField(
            value = planName,
            onValueChange = { planName = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { planColor = Color.Blue },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = planColor)
        ) {
            Text("Choose Color", color = Color.White)
        }
    }
}
