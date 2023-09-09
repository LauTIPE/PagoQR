package com.unab.desarrollowebymovil.lautipe.lectorqr

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI components
        val btnLogin: Button = findViewById(R.id.loginButton)
        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)

        // Set onClick listener for login button
        btnLogin.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Perform basic validation
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa tu nombre de usuario y contraseña", Toast.LENGTH_SHORT).show()
            } else if (username == "usuario" && password == "contraseña") {
                // Aquí puedes establecer las credenciales de ejemplo para la validación
                // Si coinciden, navega a la actividad principal
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Si las credenciales no son válidas, muestra un mensaje de error
                Toast.makeText(this, "Credenciales incorrectas, por favor inténtalo de nuevo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
