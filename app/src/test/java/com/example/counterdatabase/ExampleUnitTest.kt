package com.example.counterdatabase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.counterdatabase.data.Category
import com.example.counterdatabase.data.Pattern
import com.example.counterdatabase.data.Rarity
import com.example.counterdatabase.data.Skin
import com.example.counterdatabase.data.Weapon
import com.example.counterdatabase.ui.skins.SkinsViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SkinsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SkinsViewModel

    private val mockRarity = Rarity("rarity_common", "Common", "#ffffff")
    private val mockWeapon = Weapon("weapon_ak47", "AK-47")
    private val mockCategory = Category("cat_rifle", "Rifle")
    private val mockPattern = Pattern("pat_case_hardened", "Case Hardened")

    private val sampleSkins = listOf(
        Skin("skin-1", "AWP | Dragon Lore", "description", mockWeapon, mockCategory, mockPattern, 0.0f, 1.0f, mockRarity, false, "img_url"),
        Skin("skin-2", "AK-47 | Redline", "description", mockWeapon, mockCategory, mockPattern, 0.0f, 1.0f, mockRarity, false, "img_url"),
        Skin("skin-3", "M4A4 | Howl", "description", mockWeapon, mockCategory, mockPattern, 0.0f, 1.0f, mockRarity, false, "img_url"),
        Skin("skin-4", "AWP | Gungnir", "description", mockWeapon, mockCategory, mockPattern, 0.0f, 1.0f, mockRarity, false, "img_url"),
        Skin("skin-5", "Desert Eagle | Dragon's Breath", "description", mockWeapon, mockCategory, mockPattern, 0.0f, 1.0f, mockRarity, false, "img_url")
    )

    @Before //mudar para before Each
    fun setUp() {
        viewModel = SkinsViewModel()
        viewModel.allSkins = sampleSkins
        viewModel.searchSkins(null)
    }

    @Test
    fun `searchSkins should filter the list correctly`() {
        viewModel.searchSkins("Dragon")

        val filteredSkins = viewModel.skins.value
        assertEquals(2, filteredSkins?.size)
        assertEquals("AWP | Dragon Lore", filteredSkins?.get(0)?.name)
        assertEquals("Desert Eagle | Dragon's Breath", filteredSkins?.get(1)?.name)
    }

    @Test
    fun `searchSkins should be case-insensitive`() {
        viewModel.searchSkins("dragon")

        val filteredSkins = viewModel.skins.value
        assertEquals(2, filteredSkins?.size)
    }

    @Test
    fun `searchSkins with empty query should return the full list`() {
        viewModel.searchSkins("Dragon")
        assertEquals(2, viewModel.skins.value?.size)

        viewModel.searchSkins("")

        val fullList = viewModel.skins.value
        assertEquals(5, fullList?.size)
    }

    @Test
    fun `searchSkins with null query should return the full list`() {
        viewModel.searchSkins("Redline")
        assertEquals(1, viewModel.skins.value?.size)

        viewModel.searchSkins(null)

        val fullList = viewModel.skins.value
        assertEquals(5, fullList?.size)
    }

    @Test
    fun `searchSkins with no matching results should return an empty list`() {
        viewModel.searchSkins("NonExistentSkinName")

        val result = viewModel.skins.value
        assertEquals(0, result?.size)
    }
}
