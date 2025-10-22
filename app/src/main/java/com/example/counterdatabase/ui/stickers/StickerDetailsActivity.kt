package com.example.counterdatabase.ui.stickers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.Sticker
import com.example.counterdatabase.databinding.ActivityStickerDetailsBinding

class StickerDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStickerDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStickerDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sticker = intent.getParcelableExtra<Sticker>("sticker")

        sticker?.let {
            Glide.with(this).load(it.image).into(binding.stickerImage)
            binding.stickerName.text = it.name
            binding.stickerDescription.text = it.description
            binding.stickerRarity.text = "Rarity: ${it.rarity.name}"
            binding.stickerRarity.setTextColor(android.graphics.Color.parseColor(it.rarity.color))
            binding.stickerType.text = "Type: ${it.type}"
            binding.stickerEffect.text = "Effect: ${it.effect}"
            binding.stickerTournamentEvent.text = "Tournament: ${it.tournament_event}"
            binding.stickerTournamentTeam.text = "Team: ${it.tournament_team}"
            binding.stickerMarketHashName.text = "Market Name: ${it.market_hash_name}"
        }
    }
}
