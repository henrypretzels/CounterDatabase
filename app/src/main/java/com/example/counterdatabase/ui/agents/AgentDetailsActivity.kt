package com.example.counterdatabase.ui.agents

import android.graphics.Color
import android.os.Build
import android.os.Bundle
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

        val agent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("agent", Agent::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("agent")
        }

        agent?.let { populateUi(it) }
    }

    private fun populateUi(agent: Agent) {
        binding.agentDetailName.text = agent.name

        Glide.with(this)
            .load(agent.image)
            .into(binding.agentDetailImage)

        // Rarity and Team
        binding.agentDetailRarity.text = agent.rarity.name
        try {
            binding.agentDetailRarity.setTextColor(Color.parseColor(agent.rarity.color))
        } catch (e: IllegalArgumentException) {
            // Handle invalid color string
        }
        binding.agentDetailTeam.text = agent.team.name

        // Description with expanded manual string cleaning
        if (!agent.description.isNullOrEmpty()) {
            val cleanedDescription = agent.description
                .replace("\\n", "\n")
                .replace("\\\"", "\"")
                .replace("<i>", "")
                .replace("</i>", "")
            binding.agentDetailDescription.text = cleanedDescription
            binding.agentDetailDescription.visibility = View.VISIBLE
        } else {
            binding.agentDetailDescription.visibility = View.GONE
        }

        // Collections
        if (!agent.collections.isNullOrEmpty()) {
            val collectionsText = agent.collections.joinToString(separator = "\n") { "- ${it.name}" }
            binding.agentDetailCollections.text = collectionsText
            binding.agentDetailCollections.visibility = View.VISIBLE
            binding.agentCollectionsTitle.visibility = View.VISIBLE
        } else {
            binding.agentDetailCollections.visibility = View.GONE
            binding.agentCollectionsTitle.visibility = View.GONE
        }
    }
}
