package com.example.qta2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class DosMitadesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dos_mitades)

        // Configuración de la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los componentes de la interfaz
        val etCadena = findViewById<EditText>(R.id.etCadena)
        val btnSolucionar = findViewById<Button>(R.id.btnSolucionar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Lógica para el botón de solución
        btnSolucionar.setOnClickListener {
            val inputString = etCadena.text.toString()

            // Validar si la cadena no está vacía
            if (inputString.isNotEmpty()) {
                val result = cortarYReemplazar(inputString)
                tvResultado.text = result
            } else {
                Toast.makeText(this, "Por favor ingresa una cadena de caracteres", Toast.LENGTH_SHORT).show()
            }
        }

        // Configuración del BottomNavigationView
        bottomNav.selectedItemId = R.id.nav_mitades

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_mitades -> {
                    // Ya estamos en esta actividad, no hacer nada
                    true
                }
                R.id.nav_palabras -> {
                    // Navegar a la actividad "Dos Palabras"
                    startActivity(Intent(this, DospalabrasActivity::class.java))
                    true
                }
                R.id.nav_quitar -> {
                    // Navegar a la actividad "Quitar Fragmento"
                    startActivity(Intent(this, QuitarFragmentosActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    // Función para cortar y reemplazar las mitades de la cadena
    private fun cortarYReemplazar(input: String): String {
        // Obtener la longitud de la cadena
        val length = input.length
        val middle = length / 2

        // Si la longitud es impar, la primera mitad tendrá un carácter más
        val firstHalf = input.substring(0, middle + length % 2)
        val secondHalf = input.substring(middle + length % 2)

        // Intercambiar las mitades
        return secondHalf + firstHalf
    }
}
