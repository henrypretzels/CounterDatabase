package com.example.counterdatabase

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.counterdatabase.ui.agents.AgentsActivity
import com.example.counterdatabase.ui.crates.CratesActivity
import com.example.counterdatabase.ui.highlights.HighlightsActivity
import com.example.counterdatabase.ui.skins.SkinsActivity
import com.example.counterdatabase.ui.stickers.StickersActivity

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
            val intent = Intent(this, CratesActivity::class.java)
            startActivity(intent)
        }

        val stickersButton = findViewById<ImageButton>(R.id.stickers_button)
        stickersButton.setOnClickListener {
            val intent = Intent(this, StickersActivity::class.java)
            startActivity(intent)
        }

        val agentsButton = findViewById<ImageButton>(R.id.agents_button)
        agentsButton.setOnClickListener {
            val intent = Intent(this, AgentsActivity::class.java)
            startActivity(intent)
        }

        val highlightsButton = findViewById<ImageButton>(R.id.highlights_button)
        highlightsButton.setOnClickListener {
            val intent = Intent(this, HighlightsActivity::class.java)
            startActivity(intent)
        }
    }
}