package com.example.myappmusic

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton: Button = findViewById(R.id.playButton)
        val stopButton: Button = findViewById(R.id.stopButton)

        // Inicializace MediaPlayer se souborem my_song.mp3
        mediaPlayer = MediaPlayer.create(this, R.raw.lavender_haze)

        // Akce pro tlačítko Přehrát
        playButton.setOnClickListener {
            mediaPlayer?.let {
                if (!it.isPlaying) { // Kontrola, zda už nehraje
                    it.start()
                }
            }
        }

        // Akce pro tlačítko Zastavit
        stopButton.setOnClickListener {
            mediaPlayer?.let {
                if (it.isPlaying) { // Kontrola, zda hraje
                    it.pause()
                    it.seekTo(0) // Vrátí na začátek
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Uvolnění zdrojů MediaPlayer
        mediaPlayer?.release()
        mediaPlayer = null
    }
}