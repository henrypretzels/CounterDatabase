package com.example.counterdatabase.ui.highlights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.counterdatabase.databinding.FragmentHighlightsBinding

class HighlightsFragment : Fragment() {

    private var _binding: FragmentHighlightsBinding? = null
    private val binding get() = _binding!!

    private val highlightsViewModel: HighlightsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHighlightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HighlightsAdapter()
        binding.highlightsRecyclerView.adapter = adapter
        binding.highlightsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        highlightsViewModel.highlights.observe(viewLifecycleOwner) { highlights ->
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}