package com.example.counterdatabase.ui.crates

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.Crate
import com.example.counterdatabase.databinding.CrateItemBinding

class CratesAdapter : ListAdapter<Crate, CratesAdapter.CrateViewHolder>(CrateDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrateViewHolder {
        val binding = CrateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CrateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CrateViewHolder, position: Int) {
        val crate = getItem(position)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CrateDetailsActivity::class.java)
            intent.putExtra("crate", crate)
            holder.itemView.context.startActivity(intent)
        }
        holder.bind(crate)
    }

    class CrateViewHolder(private val binding: CrateItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(crate: Crate) {
            binding.crateName.text = crate.name
            binding.crateDescription.text = crate.description ?: ""
            binding.crateDescription.visibility = if (crate.description.isNullOrEmpty()) {
                android.view.View.GONE
            } else {
                android.view.View.VISIBLE
            }
            
            Glide.with(binding.root.context)
                .load(crate.image)
                .centerCrop()
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .placeholder(android.R.color.darker_gray)
                .error(android.R.color.darker_gray)
                .into(binding.crateImage)
        }
    }

    class CrateDiffCallback : DiffUtil.ItemCallback<Crate>() {
        override fun areItemsTheSame(oldItem: Crate, newItem: Crate): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Crate, newItem: Crate): Boolean {
            return oldItem == newItem
        }
    }
}