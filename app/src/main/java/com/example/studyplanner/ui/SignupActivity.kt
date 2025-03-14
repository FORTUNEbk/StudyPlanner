package com.example.studyplanner

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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

            if (validateInput(name, email, password, confirmPassword, nameField, emailField, passwordField, confirmPasswordField)) {
                // TODO: Implement actual registration logic (e.g., Firebase, SQLite)
                Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show()

                // Navigate to Login Activity after successful sign-up
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        // Handle Back to Login Click
        backToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    /**
     * Validates the user input fields and shows relevant error messages.
     */
    private fun validateInput(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        nameField: EditText,
        emailField: EditText,
        passwordField: EditText,
        confirmPasswordField: EditText
    ): Boolean {
        if (name.isEmpty()) {
            nameField.error = "Name is required"
            return false
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.error = "Enter a valid email"
            return false
        }
        if (password.length < 8 || !password.matches(Regex(".*[A-Za-z].*")) || !password.matches(Regex(".*[0-9].*"))) {
            passwordField.error = "Password must be at least 8 characters, include letters and numbers"
            return false
        }
        if (password != confirmPassword) {
            confirmPasswordField.error = "Passwords do not match"
            return false
        }
        return true
    }
}
