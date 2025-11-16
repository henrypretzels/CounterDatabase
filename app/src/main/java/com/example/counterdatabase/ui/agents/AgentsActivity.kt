package com.example.counterdatabase.ui.agents

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.counterdatabase.databinding.ActivityAgentsBinding

class AgentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgentsBinding
    private val viewModel: AgentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = AgentsAdapter { agent ->
            // Intent to open AgentDetailsActivity
            val intent = Intent(this, AgentDetailsActivity::class.java)
            intent.putExtra("agent", agent)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        viewModel.agents.observe(this) { agents ->
            adapter.submitList(agents)
        }

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchAgents(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchAgents(newText)
                return true
            }
        })
    }
}
