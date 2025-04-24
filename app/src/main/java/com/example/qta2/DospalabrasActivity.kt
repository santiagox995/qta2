package com.example.qta2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DospalabrasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dospalabras)

        // Referencias
        val etCadena = findViewById<EditText>(R.id.etCadena)
        val btnSolucionar = findViewById<Button>(R.id.btnSolucionar)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        btnSolucionar.setOnClickListener {
            val entrada = etCadena.text.toString().trim()
            val palabras = entrada.split(" ")

            if (palabras.size == 2 && palabras.all { it.isNotBlank() }) {
                val resultado = "${palabras[1]} ${palabras[0]}"
                Toast.makeText(this, resultado, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error: Ingresa exactamente dos palabras separadas por un espacio.", Toast.LENGTH_LONG).show()
            }
        }

        bottomNav.selectedItemId = R.id.nav_palabras

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_mitades -> {
                    startActivity(Intent(this, DosMitadesActivity::class.java))
                    true
                }
                R.id.nav_palabras -> true // Ya estás aquí
                R.id.nav_quitar -> {
                    startActivity(Intent(this, QuitarFragmentosActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
