package com.example.counterdatabase.ui.skins

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.counterdatabase.databinding.ActivitySkinsBinding

class SkinsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySkinsBinding
    private val skinsViewModel: SkinsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkinsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SkinsAdapter()
        binding.skinsRecyclerView.adapter = adapter
        binding.skinsRecyclerView.layoutManager = LinearLayoutManager(this)

        skinsViewModel.skins.observe(this) { skins ->
            adapter.submitList(skins)
        }

        binding.skinsSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                skinsViewModel.searchSkins(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                skinsViewModel.searchSkins(newText)
                return true
            }
        })

        skinsViewModel.getSkins()
    }
}