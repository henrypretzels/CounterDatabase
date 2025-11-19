package com.example.counterdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.counterdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = listOf(
            MenuItem("Skins", "Customize your looks", R.drawable.ic_skins),
            MenuItem("Crates", "Open rewards", R.drawable.ic_crates),
            MenuItem("Stickers", "Collect & use", R.drawable.ic_stickers),
            MenuItem("Agents", "Choose agents", R.drawable.ic_agents),
            MenuItem("Highlights", "Watch clips", R.drawable.ic_highlights)
        )

        binding.menuRecycler.adapter = MenuAdapter(data) { item ->
            Toast.makeText(this, "Clicked on ${item.title}", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to the corresponding activity/fragment
        }
    }
}