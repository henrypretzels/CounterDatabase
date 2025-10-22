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
        binding = ActivityStickersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = StickersAdapter()
        binding.stickersRecyclerView.adapter = adapter
        binding.stickersRecyclerView.layoutManager = LinearLayoutManager(this)

        stickersViewModel.stickers.observe(this) { stickers ->
            adapter.submitList(stickers)
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
}
