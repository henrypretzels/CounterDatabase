package com.example.counterdatabase.ui.skins

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.counterdatabase.data.Skin
import com.example.counterdatabase.databinding.ActivitySkinDetailsBinding

class SkinDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySkinDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkinDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val skin = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("skin", Skin::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Skin>("skin")
        }

        skin?.let {
            Glide.with(this)
                .load(it.image)
                .centerCrop()
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                .into(binding.skinImage)
            
            binding.skinName.text = it.name
            binding.skinWeapon.text = it.weapon.name

            if (it.description.isNullOrEmpty()) {
                binding.descriptionCard.visibility = View.GONE
            } else {
                val unescapedDescription = it.description.replace("\\n", "<br>").replace("\\\"", "\"")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.skinDescription.text = Html.fromHtml(unescapedDescription, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    @Suppress("DEPRECATION")
                    binding.skinDescription.text = Html.fromHtml(unescapedDescription)
                }
            }

            binding.skinRarity.text = it.rarity.name
            binding.skinRarity.setTextColor(android.graphics.Color.parseColor(it.rarity.color))
            binding.skinMinFloat.text = it.min_float.toString()
            binding.skinMaxFloat.text = it.max_float.toString()

            if (it.stattrak) {
                binding.stattrakContainer.visibility = View.VISIBLE
            } else {
                binding.stattrakContainer.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
