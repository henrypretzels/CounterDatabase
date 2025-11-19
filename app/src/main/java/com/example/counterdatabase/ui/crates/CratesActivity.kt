package com.example.counterdatabase.ui.crates

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.counterdatabase.databinding.ActivityCratesBinding

class CratesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCratesBinding
    private val viewModel: CratesViewModel by viewModels()
    private val adapter = CratesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            com.example.counterdatabase.data.RetrofitInstance.initialize(this)
        } catch (e: Exception) {
            android.util.Log.w("CratesActivity", "Retrofit already initialized or error", e)
        }
        
        binding = ActivityCratesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(com.example.counterdatabase.R.string.crates)

        binding.recyclerView.setHasFixedSize(false)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.crates.observe(this) { crates ->
            android.util.Log.d("CratesActivity", "Crates received: ${crates.size}")
            binding.progressBar.visibility = android.view.View.GONE
            
            if (crates.isEmpty()) {
                binding.emptyStateText.visibility = android.view.View.VISIBLE
                binding.emptyStateText.text = "Nenhum crate encontrado"
                binding.recyclerView.visibility = android.view.View.INVISIBLE
            } else {
                binding.emptyStateText.visibility = android.view.View.GONE
                binding.recyclerView.visibility = android.view.View.VISIBLE
                adapter.submitList(crates)
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchCrates(newText)
                return true
            }
        })

        viewModel.getCrates()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}