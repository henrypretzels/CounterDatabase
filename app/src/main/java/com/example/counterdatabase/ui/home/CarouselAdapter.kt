package com.example.counterdatabase.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.counterdatabase.databinding.CarouselItemBinding

data class CarouselItem(val imageUrl: String, val title: String)

class CarouselAdapter(var items: List<CarouselItem>) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    inner class CarouselViewHolder(private val binding: CarouselItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CarouselItem) {
            binding.carouselTextView.text = item.title
            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .into(binding.carouselImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = CarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}