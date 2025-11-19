package com.example.counterdatabase.ui.skins

import android.content.Intent
import android.graphics.Color
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
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, SkinDetailsActivity::class.java)
            intent.putExtra("skin", skin)
            holder.itemView.context.startActivity(intent)
        }
        holder.bind(skin)
    }

    class SkinViewHolder(private val binding: SkinItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(skin: Skin) {
            binding.skinName.text = skin.name
            binding.skinWeapon.text = skin.weapon.name
            binding.skinRarity.text = skin.rarity.name
            binding.skinRarity.setTextColor(Color.parseColor(skin.rarity.color))
            
            val floatText = "Float: ${skin.min_float} - ${skin.max_float}"
            binding.skinFloat.text = floatText
            
            if (skin.stattrak) {
                binding.skinStattrak.visibility = android.view.View.VISIBLE
                binding.skinStattrak.text = "StatTrak™"
            } else {
                binding.skinStattrak.visibility = android.view.View.GONE
            }
            
            Glide.with(binding.root.context)
                .load(skin.image)
                .centerCrop()
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .placeholder(android.R.color.darker_gray)
                .error(android.R.color.darker_gray)
                .into(binding.skinImage)
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
}