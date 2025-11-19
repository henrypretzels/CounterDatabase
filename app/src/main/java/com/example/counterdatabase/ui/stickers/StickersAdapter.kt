package com.example.counterdatabase.ui.stickers

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.Sticker
import com.example.counterdatabase.databinding.StickerItemBinding

class StickersAdapter : ListAdapter<Sticker, StickersAdapter.StickerViewHolder>(StickerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickerViewHolder {
        val binding = StickerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StickerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StickerViewHolder, position: Int) {
        val sticker = getItem(position)
        holder.bind(sticker)
        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, StickerDetailsActivity::class.java).apply {
                putExtra("sticker", sticker)
            }
            context.startActivity(intent)
        }
    }

    inner class StickerViewHolder(private val binding: StickerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sticker: Sticker) {
            binding.stickerName.text = sticker.name
            binding.stickerType.text = sticker.type

            sticker.rarity?.let {
                binding.stickerRarity.text = it.name
                try {
                    binding.stickerRarity.setTextColor(Color.parseColor(it.color))
                } catch (e: IllegalArgumentException) {
                    // In case the color string is invalid
                }
            } ?: run {
                binding.stickerRarity.text = ""
            }

            Glide.with(binding.root.context)
                .load(sticker.image)
                .into(binding.stickerImage)
        }
    }
}

class StickerDiffCallback : DiffUtil.ItemCallback<Sticker>() {
    override fun areItemsTheSame(oldItem: Sticker, newItem: Sticker): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Sticker, newItem: Sticker): Boolean {
        return oldItem == newItem
    }
}