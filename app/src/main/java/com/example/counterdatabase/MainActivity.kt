package com.example.counterdatabase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.counterdatabase.databinding.ActivityMainBinding
import com.example.counterdatabase.ui.agents.AgentsActivity
import com.example.counterdatabase.ui.crates.CratesActivity
import com.example.counterdatabase.ui.highlights.HighlightsActivity
import com.example.counterdatabase.ui.skins.SkinsActivity
import com.example.counterdatabase.ui.stickers.StickersActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    companion object {
        const val HERO_IMAGE_URL = "https://images.unsplash.com/photo-1542751371-adc38448a05e?w=1200"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        binding.toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        setupDrawerNavigation()
        loadHeroImage()
        loadDrawerHeaderImage()
        setupMenu()
    }

    private fun loadHeroImage() {
        Glide.with(this)
            .load(HERO_IMAGE_URL)
            .centerCrop()
            .skipMemoryCache(false)
            .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
            .into(binding.heroImage)
    }

    private fun loadDrawerHeaderImage() {
        val headerView = binding.navView.getHeaderView(0)
        val headerImage = headerView.findViewById<android.widget.ImageView>(R.id.headerImage)
        
        Glide.with(this)
            .load(HERO_IMAGE_URL)
            .centerCrop()
            .skipMemoryCache(false)
            .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
            .into(headerImage)
    }

    private fun setupMenu() {
        val data = listOf(
            MenuItem(
                getString(R.string.skins),
                getString(R.string.skins_description),
                R.drawable.ak47
            ),
            MenuItem(
                getString(R.string.crates),
                getString(R.string.crates_description),
                R.drawable.ic_crate_box
            ),
            MenuItem(
                getString(R.string.stickers),
                getString(R.string.stickers_description),
                R.drawable.ic_sticker
            ),
            MenuItem(
                getString(R.string.agents),
                getString(R.string.agents_description),
                R.drawable.ic_agent
            ),
            MenuItem(
                getString(R.string.highlights),
                getString(R.string.highlights_description),
                R.drawable.ic_play_highlight
            )
        )

        binding.menuRecycler.adapter = MenuAdapter(data) { item ->
            navigateToActivity(item.title)
        }
    }

    private fun setupDrawerNavigation() {
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Already on home, just close drawer
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_skins -> {
                    navigateToActivity(getString(R.string.skins))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_crates -> {
                    navigateToActivity(getString(R.string.crates))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_stickers -> {
                    navigateToActivity(getString(R.string.stickers))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_agents -> {
                    navigateToActivity(getString(R.string.agents))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_highlights -> {
                    navigateToActivity(getString(R.string.highlights))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToActivity(title: String) {
        val intent = when (title) {
            getString(R.string.skins) -> Intent(this, SkinsActivity::class.java)
            getString(R.string.crates) -> Intent(this, CratesActivity::class.java)
            getString(R.string.stickers) -> Intent(this, StickersActivity::class.java)
            getString(R.string.agents) -> Intent(this, AgentsActivity::class.java)
            getString(R.string.highlights) -> Intent(this, HighlightsActivity::class.java)
            else -> null
        }
        intent?.let { startActivity(it) }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
