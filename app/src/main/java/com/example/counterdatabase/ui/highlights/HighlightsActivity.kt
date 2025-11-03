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
        binding = ActivityHighlightsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = HighlightsAdapter()
        binding.highlightsRecyclerView.adapter = adapter
        binding.highlightsRecyclerView.layoutManager = LinearLayoutManager(this)

        highlightsViewModel.highlights.observe(this) { highlights ->
            adapter.submitList(highlights)
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
}
