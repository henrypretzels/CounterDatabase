package com.example.counterdatabase.ui.skins

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.Skin
import com.example.counterdatabase.databinding.SkinItemBinding

class SkinsAdapter : ListAdapter<Skin, SkinsAdapter.SkinViewHolder>(SkinDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinViewHolder {
        val binding = SkinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SkinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SkinViewHolder, position: Int) {
        val skin = getItem(position)
        holder.bind(skin)
        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, SkinDetailsActivity::class.java).apply {
                putExtra("skin", skin)
            }
            context.startActivity(intent)
        }
    }

    inner class SkinViewHolder(private val binding: SkinItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(skin: Skin) {
            binding.skinName.text = skin.name
            binding.skinRarity.text = skin.rarity.name
            Glide.with(binding.root.context)
                .load(skin.image)
                .into(binding.skinImage)
        }
    }
}

class SkinDiffCallback : DiffUtil.ItemCallback<Skin>() {
    override fun areItemsTheSame(oldItem: Skin, newItem: Skin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Skin, newItem: Skin): Boolean {
        return oldItem == newItem
    }
}