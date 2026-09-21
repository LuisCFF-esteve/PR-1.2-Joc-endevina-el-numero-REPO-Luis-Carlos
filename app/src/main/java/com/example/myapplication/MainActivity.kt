package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val b =findViewById<Button>(R.id.buttonToast)
        val numero= Random.nextInt(0,11)
        val et= findViewById<EditText>(R.id.getNumberInput)
        b.setOnClickListener {
            val respuesta =numero
            var text = "Toast text"
            val comparacion: Int = et.text.toString().toIntOrNull() ?:0
            if (respuesta<comparacion){
                text="El numero es menor"
            }else if (respuesta>comparacion){
                text= "El numero es mas grande"
            }else if (respuesta==comparacion){
                text= "Respuesta correcta"
            }
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(this, text, duration)
            toast.show()
        }
    }
}