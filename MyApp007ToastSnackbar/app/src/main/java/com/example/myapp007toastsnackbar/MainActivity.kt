package com.example.myapp007toastsnackbar

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp007toastsnackbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            // Inicializace ViewBinding
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Nastavení akce pro tlačítko Toast
            binding.btnShowToast.setOnClickListener {

                val toast = Toast.makeText(this, "Nazdar - mám hlad", Toast.LENGTH_LONG)

                toast.show()
            }




    }
}