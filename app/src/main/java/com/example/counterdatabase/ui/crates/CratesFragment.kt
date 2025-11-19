package com.example.counterdatabase.ui.crates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.counterdatabase.databinding.FragmentCratesBinding

class CratesFragment : Fragment() {

    private var _binding: FragmentCratesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CratesViewModel by viewModels()
    private val adapter = CratesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCratesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.crates.observe(viewLifecycleOwner) {
            adapter.submitList(it)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}