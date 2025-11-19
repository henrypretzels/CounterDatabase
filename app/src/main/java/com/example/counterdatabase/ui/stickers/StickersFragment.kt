package com.example.counterdatabase.ui.stickers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.counterdatabase.databinding.FragmentStickersBinding

class StickersFragment : Fragment() {

    private var _binding: FragmentStickersBinding? = null
    private val binding get() = _binding!!

    private val stickersViewModel: StickersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStickersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StickersAdapter()
        binding.stickersRecyclerView.adapter = adapter
        binding.stickersRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        stickersViewModel.stickers.observe(viewLifecycleOwner) { stickers ->
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}