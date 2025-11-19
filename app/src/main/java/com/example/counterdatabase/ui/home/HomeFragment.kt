package com.example.counterdatabase.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.counterdatabase.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val adapter = CarouselAdapter(emptyList()) // Start with an empty adapter
        binding.carouselViewPager.adapter = adapter

        homeViewModel.carouselItems.observe(viewLifecycleOwner) { items ->
            (binding.carouselViewPager.adapter as CarouselAdapter).apply {
                // A simple way to update the adapter's data
                // A more sophisticated approach would use DiffUtil
                this.items = items
                notifyDataSetChanged()
            }
        }

        homeViewModel.loadCarouselData()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}