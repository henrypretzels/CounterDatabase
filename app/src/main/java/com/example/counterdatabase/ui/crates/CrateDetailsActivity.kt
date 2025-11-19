package com.example.counterdatabase.ui.crates

import android.os.Build
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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val crate = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("crate", Crate::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Crate>("crate")
        }

        crate?.let {
            binding.crateName.text = it.name
            
            if (!it.type.isNullOrEmpty()) {
                binding.crateType.text = it.type
                binding.crateType.visibility = View.VISIBLE
            } else {
                binding.crateType.visibility = View.GONE
            }

            Glide.with(this)
                .load(it.image)
                .centerCrop()
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                .into(binding.crateImage)

            if (it.description.isNullOrEmpty()) {
                binding.descriptionCard.visibility = View.GONE
            } else {
                val cleanedDescription = it.description
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"")
                    .replace("<i>", "")
                    .replace("</i>", "")
                binding.crateDescription.text = cleanedDescription
            }

            if (it.contains.isNullOrEmpty()) {
                binding.containsCard.visibility = View.GONE
            } else {
                binding.containsRecyclerView.layoutManager = LinearLayoutManager(this)
                val containsAdapter = ContainedItemAdapter()
                binding.containsRecyclerView.adapter = containsAdapter
                containsAdapter.submitList(it.contains)
            }

            if (it.contains_rare.isNullOrEmpty()) {
                binding.containsRareCard.visibility = View.GONE
            } else {
                binding.containsRareRecyclerView.layoutManager = LinearLayoutManager(this)
                val containsRareAdapter = ContainedItemAdapter()
                binding.containsRareRecyclerView.adapter = containsRareAdapter
                containsRareAdapter.submitList(it.contains_rare)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
