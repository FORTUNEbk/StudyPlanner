package com.example.studyplanner.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlansScreen(onAddPlanClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Plans", fontSize = 24.sp)
            Text("+", fontSize = 28.sp, modifier = Modifier.clickable { onAddPlanClick() })
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            "You don’t have registered study plans",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Click on + to add",
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(Icons.Filled.DateRange, contentDescription = "Calendar")
            Icon(Icons.Filled.Edit, contentDescription = "Tasks")
        }
    }
}
