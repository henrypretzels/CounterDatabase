package com.example.counterdatabase

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.counterdatabase.ui.skins.SkinsActivity

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

        val skinsButton = findViewById<ImageButton>(R.id.skins_button)
        skinsButton.setOnClickListener {
            val intent = Intent(this, SkinsActivity::class.java)
            startActivity(intent)
        }

        val cratesButton = findViewById<ImageButton>(R.id.crates_button)
        cratesButton.setOnClickListener {
            Toast.makeText(this, "Crates button clicked", Toast.LENGTH_SHORT).show()
        }

        val stickersButton = findViewById<ImageButton>(R.id.stickers_button)
        stickersButton.setOnClickListener {
            Toast.makeText(this, "Stickers button clicked", Toast.LENGTH_SHORT).show()
        }

        val agentsButton = findViewById<ImageButton>(R.id.agents_button)
        agentsButton.setOnClickListener {
            Toast.makeText(this, "Agents button clicked", Toast.LENGTH_SHORT).show()
        }
    }
}