package com.example.qta2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usernameInput = findViewById<EditText>(R.id.editTextText1)
        val passwordInput = findViewById<EditText>(R.id.editTextTextPassword1)
        val loginButton = findViewById<Button>(R.id.button1)
        val backButton = findViewById<ImageView>(R.id.btnBack)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete ambos campos.", Toast.LENGTH_SHORT).show()
            } else {

                val prefs = getSharedPreferences("Credenciales", MODE_PRIVATE)
                with(prefs.edit()) {
                    putString("usuario", username)
                    putString("contrasena", password)
                    apply()
                }

                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, DosMitadesActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
