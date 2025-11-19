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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val sticker = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("sticker", Sticker::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Sticker>("sticker")
        }

        sticker?.let {
            binding.stickerName.text = it.name

            if (!it.type.isNullOrEmpty()) {
                binding.stickerType.text = it.type
                binding.stickerType.visibility = View.VISIBLE
            } else {
                binding.stickerType.visibility = View.GONE
            }

            Glide.with(this)
                .load(it.image)
                .centerCrop()
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                .into(binding.stickerImage)

            if (it.description.isNullOrEmpty()) {
                binding.descriptionCard.visibility = View.GONE
            } else {
                val unescapedDescription = it.description.replace("\\n", "<br>").replace("\\\"", "\"")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.stickerDescription.text = Html.fromHtml(unescapedDescription, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    @Suppress("DEPRECATION")
                    binding.stickerDescription.text = Html.fromHtml(unescapedDescription)
                }
            }

            if (it.rarity != null) {
                binding.rarityContainer.visibility = View.VISIBLE
                binding.stickerRarity.text = it.rarity.name
                it.rarity.color?.let { color ->
                    binding.stickerRarity.setTextColor(Color.parseColor(color))
                }
            } else {
                binding.rarityContainer.visibility = View.GONE
            }

            if (!it.type.isNullOrEmpty()) {
                binding.typeContainer.visibility = View.VISIBLE
                binding.stickerTypeDetail.text = it.type
            } else {
                binding.typeContainer.visibility = View.GONE
            }

            if (!it.effect.isNullOrEmpty()) {
                binding.effectContainer.visibility = View.VISIBLE
                binding.stickerEffect.text = it.effect
            } else {
                binding.effectContainer.visibility = View.GONE
            }

            if (!it.tournament_event.isNullOrEmpty()) {
                binding.tournamentContainer.visibility = View.VISIBLE
                binding.stickerTournamentEvent.text = it.tournament_event
            } else {
                binding.tournamentContainer.visibility = View.GONE
            }

            if (!it.tournament_team.isNullOrEmpty()) {
                binding.teamContainer.visibility = View.VISIBLE
                binding.stickerTournamentTeam.text = it.tournament_team
            } else {
                binding.teamContainer.visibility = View.GONE
            }

            if (!it.market_hash_name.isNullOrEmpty()) {
                binding.marketContainer.visibility = View.VISIBLE
                binding.stickerMarketHashName.text = it.market_hash_name
            } else {
                binding.marketContainer.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
