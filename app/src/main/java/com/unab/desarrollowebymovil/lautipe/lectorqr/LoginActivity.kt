package com.unab.desarrollowebymovil.lautipe.lectorqr

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI components
        val btnLogin: Button = findViewById(R.id.loginButton)

        //val btnSignUp: Button = findViewById(R.id.btnSignUp)

        // Set onClick listeners
        btnLogin.setOnClickListener {
            // Navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //aquí implementaría el intent de un botón para registrar una nueva cuenta
       /* btnSignUp.setOnClickListener {
            // Navigate to SignUpActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }*/

        //A partír de aquí habría que hacer las validaciones
    }
}
