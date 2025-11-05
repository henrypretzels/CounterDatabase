package com.example.counterdatabase.ui.crates

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.Crate
import com.example.counterdatabase.databinding.ActivityCrateDetailsBinding

class CrateDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrateDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrateDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val crate = intent.getParcelableExtra<Crate>("crate")

        crate?.let {
            binding.crateName.text = it.name
            Glide.with(this).load(it.image).into(binding.crateImage)

            if (it.description.isNullOrEmpty()) {
                binding.crateDescription.visibility = View.GONE
            } else {
                binding.crateDescription.text = it.description
            }

            if (it.contains.isNullOrEmpty()) {
                binding.containsTitle.visibility = View.GONE
                binding.containsRecyclerView.visibility = View.GONE
            } else {
                binding.containsRecyclerView.layoutManager = LinearLayoutManager(this)
                val containsAdapter = ContainedItemAdapter()
                binding.containsRecyclerView.adapter = containsAdapter
                containsAdapter.submitList(it.contains)
            }

            if (it.contains_rare.isNullOrEmpty()) {
                binding.containsRareTitle.visibility = View.GONE
                binding.containsRareRecyclerView.visibility = View.GONE
            } else {
                binding.containsRareRecyclerView.layoutManager = LinearLayoutManager(this)
                val containsRareAdapter = ContainedItemAdapter()
                binding.containsRareRecyclerView.adapter = containsRareAdapter
                containsRareAdapter.submitList(it.contains_rare)
            }
        }
    }
}