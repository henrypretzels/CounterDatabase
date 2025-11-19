package com.example.counterdatabase.ui.agents

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.counterdatabase.databinding.FragmentAgentsBinding

class AgentsFragment : Fragment() {

    private var _binding: FragmentAgentsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AgentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AgentsAdapter { agent ->
            val intent = Intent(requireContext(), AgentDetailsActivity::class.java)
            intent.putExtra("agent", agent)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.agents.observe(viewLifecycleOwner) { agents ->
            adapter.submitList(agents)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchAgents(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchAgents(newText)
                return true
            }
        })
        
        viewModel.getAgents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}