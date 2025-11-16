package com.example.counterdatabase.ui.agents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.Agent
import com.example.counterdatabase.databinding.AgentItemBinding

class AgentsAdapter(private val onItemClicked: (Agent) -> Unit) :
    ListAdapter<Agent, AgentsAdapter.AgentViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val binding = AgentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class AgentViewHolder(private val binding: AgentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(agent: Agent) {
            binding.agentName.text = agent.name
            binding.agentTeam.text = agent.team.name

            Glide.with(itemView.context)
                .load(agent.image)
                .into(binding.agentImage)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Agent>() {
            override fun areItemsTheSame(oldItem: Agent, newItem: Agent): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Agent, newItem: Agent): Boolean {
                return oldItem == newItem
            }
        }
    }
}