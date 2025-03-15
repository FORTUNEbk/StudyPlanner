package com.example.studyplanner.ui

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.studyplanner.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // State for user data
    var name by remember { mutableStateOf("John Doe") }
    var email by remember { mutableStateOf("johndoe@example.com") }
    var profilePicture by remember { mutableStateOf("https://via.placeholder.com/150") } // Placeholder image

    // Load saved data when the screen is launched
    LaunchedEffect(Unit) {
        val preferences = context.dataStore.data.first()
        name = preferences[PROFILE_NAME_KEY] ?: "John Doe"
        email = preferences[PROFILE_EMAIL_KEY] ?: "johndoe@example.com"
        profilePicture = preferences[PROFILE_PICTURE_KEY] ?: "https://via.placeholder.com/150"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture
            AsyncImage(
                model = profilePicture,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .clickable { /* TODO: Implement image picker here */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Name Input
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Email Input
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Save Button
            Button(
                onClick = {
                    coroutineScope.launch {
                        saveProfileData(context, name, email, profilePicture)
                        navController.popBackStack() // Navigate back after saving
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Changes", fontWeight = FontWeight.Bold)
            }
        }
    }
}

// Function to save profile data using DataStore
suspend fun saveProfileData(context: Context, name: String, email: String, profilePic: String) {
    context.dataStore.edit { preferences ->
        preferences[PROFILE_NAME_KEY] = name
        preferences[PROFILE_EMAIL_KEY] = email
        preferences[PROFILE_PICTURE_KEY] = profilePic
    }
}

// DataStore keys for profile data
val PROFILE_NAME_KEY = stringPreferencesKey("profile_name")
val PROFILE_EMAIL_KEY = stringPreferencesKey("profile_email")
val PROFILE_PICTURE_KEY = stringPreferencesKey("profile_picture")
