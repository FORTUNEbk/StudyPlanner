package com.example.studyplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studyplanner.ui.LoginActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize UI elements
        val nameField = findViewById<EditText>(R.id.name)
        val emailField = findViewById<EditText>(R.id.signup_email)
        val passwordField = findViewById<EditText>(R.id.signup_password)
        val confirmPasswordField = findViewById<EditText>(R.id.confirm_password)
        val signupButton = findViewById<Button>(R.id.signup_button)
        val backToLogin = findViewById<TextView>(R.id.back_to_login)

        // Handle Sign-Up Button Click
        signupButton.setOnClickListener {
            val name = nameField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            val confirmPassword = confirmPasswordField.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Implement actual registration logic
                Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show()
                // Example: Navigate to Login Activity after successful sign-up
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        // Handle Back to Login Click
        backToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
