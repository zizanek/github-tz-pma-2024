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

        // Změna obrázku v závislosti na vybraném radioButtonu

        binding.rbKolo1.setOnClickListener {
            binding.ivKolo.setImageResource(R.drawable.oiz_m10_tr)
        }

        binding.rbKolo2.setOnClickListener {
            binding.ivKolo.setImageResource(R.drawable.oiz_m20_tr)
        }

        binding.rbKolo3.setOnClickListener {
            binding.ivKolo.setImageResource(R.drawable.oiz_m30_tr)
        }

    }
}