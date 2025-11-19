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
        
        try {
            com.example.counterdatabase.data.RetrofitInstance.initialize(this)
        } catch (e: Exception) {
            android.util.Log.w("SkinsActivity", "Retrofit already initialized or error", e)
        }
        
        binding = ActivitySkinsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(com.example.counterdatabase.R.string.skins)

        val adapter = SkinsAdapter()
        binding.skinsRecyclerView.setHasFixedSize(false)
        binding.skinsRecyclerView.adapter = adapter
        binding.skinsRecyclerView.layoutManager = LinearLayoutManager(this)

        skinsViewModel.skins.observe(this) { skins ->
            android.util.Log.d("SkinsActivity", "=== Observer triggered ===")
            android.util.Log.d("SkinsActivity", "Skins received: ${skins.size}")
            android.util.Log.d("SkinsActivity", "Skins list: ${skins.take(3).map { it.name }}")
            
            binding.progressBar.visibility = android.view.View.GONE
            
            if (skins.isEmpty()) {
                android.util.Log.w("SkinsActivity", "List is empty, showing empty state")
                binding.emptyStateText.visibility = android.view.View.VISIBLE
                binding.emptyStateText.text = "Nenhuma skin encontrada\nVerifique sua conexão com a internet"
                binding.skinsRecyclerView.visibility = android.view.View.INVISIBLE
            } else {
                android.util.Log.d("SkinsActivity", "List has ${skins.size} items, showing RecyclerView")
                binding.emptyStateText.visibility = android.view.View.GONE
                binding.skinsRecyclerView.visibility = android.view.View.VISIBLE
                adapter.submitList(skins)
            }
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}