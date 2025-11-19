package com.example.counterdatabase.ui.agents

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.counterdatabase.databinding.ActivityAgentsBinding

class AgentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgentsBinding
    private val agentsViewModel: AgentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            com.example.counterdatabase.data.RetrofitInstance.initialize(this)
        } catch (e: Exception) {
            android.util.Log.w("AgentsActivity", "Retrofit already initialized or error", e)
        }
        
        binding = ActivityAgentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(com.example.counterdatabase.R.string.agents)

        val adapter = AgentsAdapter()
        binding.agentsRecyclerView.setHasFixedSize(false)
        binding.agentsRecyclerView.adapter = adapter
        binding.agentsRecyclerView.layoutManager = LinearLayoutManager(this)

        agentsViewModel.agents.observe(this) { agents ->
            android.util.Log.d("AgentsActivity", "Agents received: ${agents.size}")
            binding.progressBar.visibility = android.view.View.GONE
            
            if (agents.isEmpty()) {
                binding.emptyStateText.visibility = android.view.View.VISIBLE
                binding.emptyStateText.text = "Nenhum agente encontrado"
                binding.agentsRecyclerView.visibility = android.view.View.INVISIBLE
            } else {
                binding.emptyStateText.visibility = android.view.View.GONE
                binding.agentsRecyclerView.visibility = android.view.View.VISIBLE
                adapter.submitList(agents)
            }
        }

        binding.agentsSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                agentsViewModel.searchAgents(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                agentsViewModel.searchAgents(newText)
                return true
            }
        })

        agentsViewModel.getAgents()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}

