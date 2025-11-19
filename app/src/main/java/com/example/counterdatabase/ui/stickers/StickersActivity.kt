package com.example.counterdatabase.ui.stickers

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.counterdatabase.databinding.ActivityStickersBinding

class StickersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStickersBinding
    private val stickersViewModel: StickersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            com.example.counterdatabase.data.RetrofitInstance.initialize(this)
        } catch (e: Exception) {
            android.util.Log.w("StickersActivity", "Retrofit already initialized or error", e)
        }
        
        binding = ActivityStickersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(com.example.counterdatabase.R.string.stickers)

        val adapter = StickersAdapter()
        binding.stickersRecyclerView.setHasFixedSize(false)
        binding.stickersRecyclerView.adapter = adapter
        binding.stickersRecyclerView.layoutManager = LinearLayoutManager(this)

        stickersViewModel.stickers.observe(this) { stickers ->
            android.util.Log.d("StickersActivity", "Stickers received: ${stickers.size}")
            binding.progressBar.visibility = android.view.View.GONE
            
            if (stickers.isEmpty()) {
                binding.emptyStateText.visibility = android.view.View.VISIBLE
                binding.emptyStateText.text = "Nenhum sticker encontrado"
                binding.stickersRecyclerView.visibility = android.view.View.INVISIBLE
            } else {
                binding.emptyStateText.visibility = android.view.View.GONE
                binding.stickersRecyclerView.visibility = android.view.View.VISIBLE
                adapter.submitList(stickers)
            }
        }

        binding.stickersSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                stickersViewModel.searchStickers(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                stickersViewModel.searchStickers(newText)
                return true
            }
        })

        stickersViewModel.getStickers()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
