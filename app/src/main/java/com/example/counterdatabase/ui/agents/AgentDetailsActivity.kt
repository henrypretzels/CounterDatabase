package com.example.counterdatabase.ui.agents

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.Agent
import com.example.counterdatabase.databinding.ActivityAgentDetailsBinding

class AgentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val agent = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("agent", Agent::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Agent>("agent")
        }

        agent?.let {
            binding.agentName.text = it.name

            Glide.with(this)
                .load(it.image)
                .centerCrop()
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                .into(binding.agentImage)

            if (it.description.isNullOrEmpty()) {
                binding.descriptionCard.visibility = View.GONE
            } else {
                val unescapedDescription = it.description.replace("\\n", "<br>").replace("\\\"", "\"")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.agentDescription.text = Html.fromHtml(unescapedDescription, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    @Suppress("DEPRECATION")
                    binding.agentDescription.text = Html.fromHtml(unescapedDescription)
                }
            }

            if (it.team != null) {
                binding.teamContainer.visibility = View.VISIBLE
                binding.agentTeam.text = it.team.name
            } else {
                binding.teamContainer.visibility = View.GONE
            }

            if (it.rarity != null) {
                binding.rarityContainer.visibility = View.VISIBLE
                binding.agentRarity.text = it.rarity.name
                binding.agentRarity.setTextColor(android.graphics.Color.parseColor(it.rarity.color))
            } else {
                binding.rarityContainer.visibility = View.GONE
            }

            if (it.collections.isNullOrEmpty()) {
                binding.collectionsCard.visibility = View.GONE
            } else {
                binding.collectionsCard.visibility = View.VISIBLE
                val collectionsText = it.collections.joinToString("\n") { collection -> "• ${collection.name}" }
                binding.agentCollections.text = collectionsText
            }

            if (it.market_hash_name.isNullOrEmpty()) {
                binding.marketContainer.visibility = View.GONE
            } else {
                binding.marketContainer.visibility = View.VISIBLE
                binding.agentMarketHashName.text = it.market_hash_name
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}

