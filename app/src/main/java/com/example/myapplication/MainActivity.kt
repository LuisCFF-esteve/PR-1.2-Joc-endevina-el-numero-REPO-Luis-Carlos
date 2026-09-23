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
        val et= findViewById<EditText>(R.id.getNumberInput)
        val tv= findViewById<TextView>(R.id.textViewContador)
        val tvText= tv.text
        var numero= Random.nextInt(1,101)
        b.setOnClickListener {
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
                text= "Respuesta correcta el numero es $numero"
                et.text.clear()
                toastCLass(text)
                numero= Random.nextInt(1,101)
                tv.text=tvText
            }
        }
    }

    private fun toastCLass(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}