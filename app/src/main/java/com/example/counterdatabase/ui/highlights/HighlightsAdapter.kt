package com.example.counterdatabase.ui.highlights

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.Highlight
import com.example.counterdatabase.databinding.HighlightItemBinding

class HighlightsAdapter : ListAdapter<Highlight, HighlightsAdapter.HighlightViewHolder>(HighlightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val binding = HighlightItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HighlightViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {
        val highlight = getItem(position)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, HighlightDetailsActivity::class.java)
            intent.putExtra("highlight", highlight)
            holder.itemView.context.startActivity(intent)
        }
        holder.bind(highlight)
    }

    class HighlightViewHolder(private val binding: HighlightItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(highlight: Highlight) {
            binding.highlightName.text = highlight.name
            Glide.with(binding.root.context)
                .load(highlight.image)
                .centerCrop()
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .placeholder(android.R.color.darker_gray)
                .error(android.R.color.darker_gray)
                .into(binding.highlightImage)
        }
    }

    class HighlightDiffCallback : DiffUtil.ItemCallback<Highlight>() {
        override fun areItemsTheSame(oldItem: Highlight, newItem: Highlight): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Highlight, newItem: Highlight): Boolean {
            return oldItem == newItem
        }
    }
}
