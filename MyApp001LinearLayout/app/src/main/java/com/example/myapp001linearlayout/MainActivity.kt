package com.example.myapp001linearlayout

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val etName = findViewById<EditText>(R.id.etName)
        val etSurname = findViewById<EditText>(R.id.etSurname)
        val etPlace = findViewById<EditText>(R.id.etPlace)
        val etAge = findViewById<EditText>(R.id.etAge)
        val tvInformation = findViewById<TextView>(R.id.tvInformation)
        val bntSend = findViewById<Button>(R.id.btnSend)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        // Nastavení obsluhy pro tlačítko Odeslat
        bntSend.setOnClickListener {
            val name = etName.text.toString()
            val surname = etSurname.text.toString()
            val place = etPlace.text.toString()
            val age = etAge.text.toString()

            // Zobrazení textu v TextView
            val formattedText =
                "Jmenuji se $name $surname. Je mi $age let a moje bydliště je $place."
            tvInformation.text = formattedText
        }

        // Nastavení obsluhy pro tlačítko Odeslat
        btnDelete.setOnClickListener {
            etName.text.clear()
            etSurname.text.clear()
            etPlace.text.clear()
            etAge.text.clear()

            tvInformation.text = ""
        }
    }
}