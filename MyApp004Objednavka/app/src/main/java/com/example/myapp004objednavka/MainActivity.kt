package com.example.myapp004objednavka

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp004objednavka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()


        // binding settings
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Objednávka kola"

        binding.btnObjednavka.setOnClickListener {

            // načtení ID vybraného radioButtonu z radioGroup
            val koloRbId = binding.rgKola.checkedRadioButtonId

            val kolo = findViewById<RadioButton>(koloRbId)

            val vidlice = binding.cbVidlice.isChecked
            val sedlo = binding.cbSedlo.isChecked
            val riditka = binding.cbRiditka.isChecked

            val objednavkaText = "Souhrn objednávky: " +
                    "${kolo.text}" +
                    (if(vidlice) "; lepší vidlice" else "") +
                    (if(sedlo) "; lepší sedlo" else "") +
                    (if(riditka) "; vytuněná karbonová řidítka" else "")

            binding.tvObjednavka.text = objednavkaText

        }
        







    }
}