package com.example.counterdatabase.ui.crates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.ContainedItem
import com.example.counterdatabase.databinding.ContainedItemBinding

class ContainedItemAdapter : ListAdapter<ContainedItem, ContainedItemAdapter.ContainedItemViewHolder>(ContainedItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContainedItemViewHolder {
        val binding = ContainedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContainedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContainedItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ContainedItemViewHolder(private val binding: ContainedItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ContainedItem) {
            binding.itemName.text = item.name
            Glide.with(binding.root.context)
                .load(item.image)
                .into(binding.itemImage)
        }
    }

    class ContainedItemDiffCallback : DiffUtil.ItemCallback<ContainedItem>() {
        override fun areItemsTheSame(oldItem: ContainedItem, newItem: ContainedItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContainedItem, newItem: ContainedItem): Boolean {
            return oldItem == newItem
        }
    }
}