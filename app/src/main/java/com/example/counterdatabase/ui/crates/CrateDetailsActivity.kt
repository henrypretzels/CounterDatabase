package com.example.counterdatabase.ui.crates

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.ContainedItem
import com.example.counterdatabase.data.Crate
import com.example.counterdatabase.databinding.ActivityCrateDetailsBinding
import com.example.counterdatabase.ui.highlights.getParcelableExtraCompat

class CrateDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrateDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrateDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val crate = intent.getParcelableExtraCompat<Crate>("crate")

        crate?.let {
            binding.collapsingToolbar.title = it.name
            binding.crateDetailType.text = "Type: ${it.type}"

            // Handle description with formatting
            if (!it.description.isNullOrEmpty()) {
                val cleanedDescription = it.description
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"")
                binding.crateDetailDescription.text = cleanedDescription
                binding.crateDetailDescription.visibility = View.VISIBLE
            } else {
                binding.crateDetailDescription.visibility = View.GONE
            }

            Glide.with(this)
                .load(it.image)
                .into(binding.crateDetailImage)

            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(crate: Crate) {
        val adapter = ContainedItemAdapter()
        binding.containedItemsRecyclerView.adapter = adapter
        binding.containedItemsRecyclerView.layoutManager = LinearLayoutManager(this)

        val allItems = mutableListOf<ContainedItem>()
        crate.contains?.let { allItems.addAll(it) }
        crate.contains_rare?.let { allItems.addAll(it) }

        adapter.submitList(allItems)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}