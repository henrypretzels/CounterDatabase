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

        val skin = intent.getParcelableExtra<Skin>("skin")

        skin?.let {
            Glide.with(this).load(it.image).into(binding.skinImage)
            binding.skinName.text = it.name

            if (it.description.isNullOrEmpty()) {
                binding.skinDescription.visibility = View.GONE
            } else {
                val unescapedDescription = it.description.replace("\\n", "<br>").replace("\\\"", "\"")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.skinDescription.text = Html.fromHtml(unescapedDescription, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    @Suppress("DEPRECATION")
                    binding.skinDescription.text = Html.fromHtml(unescapedDescription)
                }
            }

            binding.skinWeapon.text = "Weapon: ${it.weapon.name}"
            binding.skinRarity.text = "Rarity: ${it.rarity.name}"
            binding.skinMinFloat.text = "Min Float: ${it.min_float}"
            binding.skinMaxFloat.text = "Max Float: ${it.max_float}"
        }
    }
}
