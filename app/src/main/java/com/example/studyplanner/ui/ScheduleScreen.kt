package com.example.studyplanner.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip

@Composable
fun ScheduleScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Schedule", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Calendar", fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "My Tasks", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
            imageVector = Icons.Default.Edit, // Placeholder icon
            contentDescription = "Tasks Icon",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "You don’t have any scheduled tasks", color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScheduleScreen() {
    ScheduleScreen()
}
