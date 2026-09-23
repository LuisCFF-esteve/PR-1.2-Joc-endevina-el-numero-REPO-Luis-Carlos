package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left+20, systemBars.top+20, systemBars.right+20, systemBars.bottom+20)
            insets

        }

        val b =findViewById<Button>(R.id.buttonToast)
        val et= findViewById<EditText>(R.id.getNumberInput)
        val tv= findViewById<TextView>(R.id.textViewContador)
        val tvText= tv.text
        var intent =0
        val tvIntents=findViewById<TextView>(R.id.textViewIntentos)
        tvIntents.setText("Intentos: $intent")
        var numero= Random.nextInt(1,101)
        b.setOnClickListener {
            intent++
            tvIntents.setText("Intentos: $intent")
            var text: String
            val comparacion: Int = et.text.toString().toIntOrNull() ?:0
            if (numero < comparacion){
                text="El numero es menor"
                tv.text = "${tv.text} $comparacion, "
                toastCLass(text)
                et.text.clear()
            }else if (numero > comparacion){
                text= "El numero es mas grande"
                tv.text= "${tv.text} $comparacion, "
                toastCLass(text)
                et.text.clear()
            }else {
                val puntuacion = intent
                toastCLass("Respuesta correcta el numero es $numero")
                et.text.clear()
                mostrarDialogoNombre(puntuacion) {
                    numero = Random.nextInt(1, 101)
                    intent = 0
                    tv.text = tvText
                    tvIntents.setText("Intentos: $intent")
                }
            }
        }
    }

    private fun mostrarDialogoNombre(puntuacion: Int, alCerrar: () -> Unit) {
        val inputNombre = EditText(this)
        inputNombre.hint = "Tu nombre"

        AlertDialog.Builder(this)
            .setTitle("Partida acabada")
            .setMessage("Puntuación: $puntuacion intentos.\n¿Quieres guardar tu nombre?")
            .setView(inputNombre)
            .setCancelable(false)
            .setPositiveButton("Guardar") { _, _ ->
                val nombre = inputNombre.text.toString().trim()
                if (nombre.isEmpty()) {
                    toastCLass("No se ha guardado: falta el nombre")
                } else {
                    toastCLass("$nombre — $puntuacion intentos")
                }
                alCerrar()
            }
            .setNegativeButton("Ahora no") { _, _ ->
                alCerrar()
            }
            .show()
    }
    private fun toastCLass(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}