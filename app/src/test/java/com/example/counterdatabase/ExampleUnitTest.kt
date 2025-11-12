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

/**
 * A clean and focused unit test for the SkinsViewModel.
 * This test verifies the search/filter functionality.
 */
class SkinsViewModelTest {

    // This rule is needed to test LiveData and ensures that LiveData updates happen instantly.
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SkinsViewModel

    // Mock data for testing
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

    @Before
    fun setUp() {
        viewModel = SkinsViewModel()
        // Directly set the mock list on the ViewModel for testing
        viewModel.allSkins = sampleSkins
        // Set the initial value for the LiveData
        viewModel.searchSkins(null)
    }

    @Test
    fun `searchSkins should filter the list correctly`() {
        // WHEN searching for "Dragon"
        viewModel.searchSkins("Dragon")

        // THEN the LiveData should contain only skins with "Dragon" in their name
        val filteredSkins = viewModel.skins.value
        assertEquals(2, filteredSkins?.size)
        assertEquals("AWP | Dragon Lore", filteredSkins?.get(0)?.name)
        assertEquals("Desert Eagle | Dragon's Breath", filteredSkins?.get(1)?.name)
    }

    @Test
    fun `searchSkins should be case-insensitive`() {
        // WHEN searching for "dragon" (lowercase)
        viewModel.searchSkins("dragon")

        // THEN the LiveData should still find the skins
        val filteredSkins = viewModel.skins.value
        assertEquals(2, filteredSkins?.size)
    }

    @Test
    fun `searchSkins with empty query should return the full list`() {
        // GIVEN a filtered list
        viewModel.searchSkins("Dragon")
        assertEquals(2, viewModel.skins.value?.size)

        // WHEN searching with an empty query
        viewModel.searchSkins("")

        // THEN the LiveData should contain the full, unfiltered list
        val fullList = viewModel.skins.value
        assertEquals(5, fullList?.size)
    }

    @Test
    fun `searchSkins with null query should return the full list`() {
        // GIVEN a filtered list
        viewModel.searchSkins("Redline")
        assertEquals(1, viewModel.skins.value?.size)

        // WHEN searching with a null query
        viewModel.searchSkins(null)

        // THEN the LiveData should contain the full, unfiltered list
        val fullList = viewModel.skins.value
        assertEquals(5, fullList?.size)
    }

    @Test
    fun `searchSkins with no matching results should return an empty list`() {
        // WHEN searching for a query that doesn't match any skin
        viewModel.searchSkins("NonExistentSkinName")

        // THEN the LiveData should contain an empty list
        val result = viewModel.skins.value
        assertEquals(0, result?.size)
    }
}
