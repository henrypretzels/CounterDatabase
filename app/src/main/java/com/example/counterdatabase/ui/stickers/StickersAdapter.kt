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
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, StickerDetailsActivity::class.java)
            intent.putExtra("sticker", sticker)
            holder.itemView.context.startActivity(intent)
        }
        holder.bind(sticker)
    }

    class StickerViewHolder(private val binding: StickerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sticker: Sticker) {
            binding.stickerName.text = sticker.name
            binding.stickerRarity.text = sticker.rarity.name
            binding.stickerRarity.setTextColor(Color.parseColor(sticker.rarity.color))
            binding.stickerType.text = sticker.type
            Glide.with(binding.root.context)
                .load(sticker.image)
                .into(binding.stickerImage)
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
}
