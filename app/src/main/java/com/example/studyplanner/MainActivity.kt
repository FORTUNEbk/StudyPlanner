package com.example.studyplanner

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.studyplanner.ui.NewPlanScreen
import com.example.studyplanner.ui.PlansScreen
import com.example.studyplanner.ui.ScheduleScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val isLoggedIn = withContext(Dispatchers.IO) { getLoginState() }
            setContent {
                StudyPlannerApp(isLoggedIn)
            }
        }
    }

    private suspend fun getLoginState(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[booleanPreferencesKey("remember_me")] ?: false
    }
}

@Composable
fun StudyPlannerApp(isLoggedIn: Boolean) {
    val navController = rememberNavController()

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate("plans") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Scaffold { paddingValues ->
        NavigationGraph(navController, Modifier.padding(paddingValues))
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = "login", modifier = modifier) {
        composable("login") { LoginScreen(navController) }
        composable("plans") { PlansScreen { navController.navigate("new_plan") } }
        composable("new_plan") { NewPlanScreen() }
        composable("schedule") { ScheduleScreen() }
        composable("settings") { SettingsScreen(navController) }
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Study Planner Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it }
            )
            Text("Remember Me", modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(10.dp))
        }

        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    errorMessage = "Please fill in all fields"
                } else if (email == "user@example.com" && password == "password123") {
                    coroutineScope.launch {
                        context.dataStore.edit { preferences ->
                            preferences[booleanPreferencesKey("remember_me")] = rememberMe
                        }
                    }

                    navController.navigate("plans") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    errorMessage = "Invalid email or password"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    context.dataStore.edit { preferences ->
                        preferences[booleanPreferencesKey("remember_me")] = false
                    }
                    navController.navigate("login") {
                        popUpTo("plans") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(rememberNavController())
}
