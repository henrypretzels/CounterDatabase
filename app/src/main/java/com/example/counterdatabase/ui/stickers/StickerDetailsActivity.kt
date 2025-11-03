package com.example.counterdatabase.ui.stickers

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
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

            if (it.description.isNullOrEmpty()) {
                binding.stickerDescription.visibility = View.GONE
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.stickerDescription.text = Html.fromHtml(it.description, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    binding.stickerDescription.text = Html.fromHtml(it.description)
                }
            }

            if (it.rarity?.name.isNullOrEmpty()) {
                binding.stickerRarity.visibility = View.GONE
            } else {
                binding.stickerRarity.text = "Rarity: ${it.rarity?.name}"
                it.rarity?.color?.let { color ->
                    binding.stickerRarity.setTextColor(Color.parseColor(color))
                }
            }

            if (it.type.isNullOrEmpty()) {
                binding.stickerType.visibility = View.GONE
            } else {
                binding.stickerType.text = "Type: ${it.type}"
            }

            if (it.effect.isNullOrEmpty()) {
                binding.stickerEffect.visibility = View.GONE
            } else {
                binding.stickerEffect.text = "Effect: ${it.effect}"
            }

            if (it.tournament_event.isNullOrEmpty()) {
                binding.stickerTournamentEvent.visibility = View.GONE
            } else {
                binding.stickerTournamentEvent.text = "Tournament: ${it.tournament_event}"
            }

            if (it.tournament_team.isNullOrEmpty()) {
                binding.stickerTournamentTeam.visibility = View.GONE
            } else {
                binding.stickerTournamentTeam.text = "Team: ${it.tournament_team}"
            }

            if (it.market_hash_name.isNullOrEmpty()) {
                binding.stickerMarketHashName.visibility = View.GONE
            } else {
                binding.stickerMarketHashName.text = "Market Name: ${it.market_hash_name}"
            }
        }
    }
}