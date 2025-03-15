package com.example.studyplanner.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun PlansScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Light gray background
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "My Study Plans",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = { navController.navigate("add_plan") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add New Plan")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Back to Home")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlansScreenPreview() {
    PlansScreen(navController = rememberNavController()) // Uses a placeholder navController
}
