package com.example.studyplanner

import SettingsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.CalendarMonth  // ✅ Corrected Import
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.studyplanner.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyPlannerApp()
        }
    }
}

@Composable
fun StudyPlannerApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->
        NavigationGraph(navController, Modifier.padding(paddingValues))
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = "plans", modifier = modifier) {
        composable("plans") { PlansScreen { navController.navigate("new_plan") } }
        composable("new_plan") { NewPlanScreen() }
        composable("schedule") { ScheduleScreen() }
        composable("settings") { SettingsScreen() }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Plans", "plans", Icons.Filled.DateRange),
        BottomNavItem("Schedule", "schedule", Icons.Filled.CalendarMonth),  // ✅ Fixed the icon
        BottomNavItem("Settings", "settings", Icons.Filled.Settings)
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) }
            )
        }
    }
}

data class BottomNavItem(val label: String, val route: String, val icon: ImageVector)
