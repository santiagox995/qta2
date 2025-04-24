package com.example.qta2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.bottomnavigation.BottomNavigationView

class QuitarFragmentosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quitar_fragmentos)

        val btnSolucionar = findViewById<Button>(R.id.btnSolucionar)
        val etCadena = findViewById<TextInputEditText>(R.id.etCadena)
        val tvResultado = findViewById<TextView>(R.id.tvInstrucciones)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Configurar el listener para la acción de aplicar la lógica
        btnSolucionar.setOnClickListener {
            val cadena = etCadena.text.toString().trim()

            // Verificar si la cadena no está vacía antes de procesar
            if (cadena.isNotEmpty()) {
                // Llamar a la función que realiza la operación
                val resultado = quitarFragmentos(cadena)

                // Mostrar el resultado o mensaje de error
                if (resultado != null) {
                    tvResultado.text = "Resultado: $resultado"
                } else {
                    // En caso de que no se encuentren 2 'h's
                    tvResultado.text = ""
                    Toast.makeText(this, "La cadena debe contener al menos dos 'h'", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Si la cadena está vacía
                tvResultado.text = ""
                Toast.makeText(this, "Por favor ingresa una cadena de texto.", Toast.LENGTH_SHORT).show()
            }
        }

        // Ajustes de barra de estado para un diseño Edge-to-Edge
        try {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        } catch (e: Exception) {
            // Mostrar error si ocurre un fallo con el ajuste de la barra de estado
            Toast.makeText(this, "Error al configurar la barra de estado: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        // Configuración del Bottom Navigation
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_mitades -> {
                    startActivity(Intent(this, DosMitadesActivity::class.java))
                    true
                }
                R.id.nav_palabras -> {
                    // Navegar a la actividad "Dos Palabras"
                    startActivity(Intent(this, DospalabrasActivity::class.java))
                    true
                }
                R.id.nav_quitar -> {
                    // Ya estamos en esta actividad, no hacer nada
                    true
                }
                else -> false
            }
        }
    }

    // Función que procesa la cadena
    private fun quitarFragmentos(cadena: String): String? {
        // Verificar que la cadena contenga al menos dos 'h'
        val primeraH = cadena.indexOf('h')
        val ultimaH = cadena.lastIndexOf('h')

        // Si no hay dos 'h' en la cadena, retornamos null
        if (primeraH == -1 || ultimaH == -1 || primeraH == ultimaH) {
            return null
        }

        // Extraemos la parte anterior a la primera 'h' y posterior a la última 'h'
        val resultado = cadena.substring(0, primeraH) + cadena.substring(ultimaH + 1)

        return resultado
    }
}
