package com.example.counterdatabase.ui.highlights

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.counterdatabase.databinding.ActivityHighlightsBinding

class HighlightsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHighlightsBinding
    private val highlightsViewModel: HighlightsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            com.example.counterdatabase.data.RetrofitInstance.initialize(this)
        } catch (e: Exception) {
            android.util.Log.w("HighlightsActivity", "Retrofit already initialized or error", e)
        }
        
        binding = ActivityHighlightsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(com.example.counterdatabase.R.string.highlights)

        val adapter = HighlightsAdapter()
        binding.highlightsRecyclerView.setHasFixedSize(false)
        binding.highlightsRecyclerView.adapter = adapter
        binding.highlightsRecyclerView.layoutManager = LinearLayoutManager(this)

        highlightsViewModel.highlights.observe(this) { highlights ->
            android.util.Log.d("HighlightsActivity", "Highlights received: ${highlights.size}")
            binding.progressBar.visibility = android.view.View.GONE
            
            if (highlights.isEmpty()) {
                binding.emptyStateText.visibility = android.view.View.VISIBLE
                binding.emptyStateText.text = "Nenhum highlight encontrado"
                binding.highlightsRecyclerView.visibility = android.view.View.INVISIBLE
            } else {
                binding.emptyStateText.visibility = android.view.View.GONE
                binding.highlightsRecyclerView.visibility = android.view.View.VISIBLE
                adapter.submitList(highlights)
            }
        }

        binding.highlightsSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                highlightsViewModel.searchHighlights(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                highlightsViewModel.searchHighlights(newText)
                return true
            }
        })

        highlightsViewModel.getHighlights()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
