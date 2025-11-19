package com.example.counterdatabase.ui.agents

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.Agent
import com.example.counterdatabase.databinding.AgentItemBinding

class AgentsAdapter : ListAdapter<Agent, AgentsAdapter.AgentViewHolder>(AgentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val binding = AgentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val agent = getItem(position)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AgentDetailsActivity::class.java)
            intent.putExtra("agent", agent)
            holder.itemView.context.startActivity(intent)
        }
        holder.bind(agent)
    }

    class AgentViewHolder(private val binding: AgentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(agent: Agent) {
            binding.agentName.text = agent.name
            
            agent.team?.let {
                binding.agentTeam.text = it.name
                binding.agentTeam.visibility = android.view.View.VISIBLE
            } ?: run {
                binding.agentTeam.visibility = android.view.View.GONE
            }
            
            agent.rarity?.let {
                binding.agentRarity.text = it.name
                binding.agentRarity.setTextColor(Color.parseColor(it.color))
                binding.agentRarity.visibility = android.view.View.VISIBLE
            } ?: run {
                binding.agentRarity.visibility = android.view.View.GONE
            }
            
            Glide.with(binding.root.context)
                .load(agent.image)
                .centerCrop()
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .placeholder(android.R.color.darker_gray)
                .error(android.R.color.darker_gray)
                .into(binding.agentImage)
        }
    }

    class AgentDiffCallback : DiffUtil.ItemCallback<Agent>() {
        override fun areItemsTheSame(oldItem: Agent, newItem: Agent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Agent, newItem: Agent): Boolean {
            return oldItem == newItem
        }
    }
}

