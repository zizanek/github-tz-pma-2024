package com.example.myapp008afragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myapp008afragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnFragment1.setOnClickListener {
          replaceFragment(Fragment1())
        }

        binding.btnFragment2.setOnClickListener {
            replaceFragment(Fragment2())
        }
    }

    private fun replaceFragment(fragment : Fragment) {

        // získá instanci správce fragmentů
        val fragmentManager = supportFragmentManager

        // vytvoří novou transkakci s fragmenty
        val fragmentTransaction = fragmentManager.beginTransaction()

        // nahradí fragment v kontejneru novým fragmentem, který byl předán jako argument
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)

        // potvrdí transakci a provede výměne fragmentu
        fragmentTransaction.commit()
    }


}