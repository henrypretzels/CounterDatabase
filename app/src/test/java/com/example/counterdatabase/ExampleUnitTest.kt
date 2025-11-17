package com.example.counterdatabase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.counterdatabase.data.Category
import com.example.counterdatabase.data.ContainedItem
import com.example.counterdatabase.data.Crate
import com.example.counterdatabase.data.Pattern
import com.example.counterdatabase.data.Rarity
import com.example.counterdatabase.data.Skin
import com.example.counterdatabase.data.Sticker
import com.example.counterdatabase.data.Weapon
import com.example.counterdatabase.ui.crates.CratesViewModel
import com.example.counterdatabase.ui.skins.SkinsViewModel
import com.example.counterdatabase.ui.stickers.StickersViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.lang.reflect.Field
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


class ExampleUnitTest {

    // force synchronous execution of LiveData in tests
    @get:Rule
    val instantExecutorRule: TestRule = InstantTaskExecutorRule()

    // ========== Data Mocks ==========

    private val mockRarity = Rarity("rarity_common", "Common", "#ffffff")
    private val mockWeapon = Weapon("weapon_ak47", "AK-47")
    private val mockCategory = Category("cat_rifle", "Rifle")
    private val mockPattern = Pattern("pat_case_hardened", "Case Hardened")
    private val mockCrateForStickers = Crate(
        id = "crate_1",
        name = "Community Sticker Capsule 1",
        description = "A collection of community-created stickers.",
        type = "Sticker Capsule",
        first_sale_date = "2014-02-20",
        contains = null,
        contains_rare = null,
        image = "image_url_crate",
        loot_list = null
    )

    // ========== ViewModels ==========

    private lateinit var skinsViewModel: SkinsViewModel
    private lateinit var stickersViewModel: StickersViewModel
    private lateinit var cratesViewModel: CratesViewModel

    @Before
    fun setUp() {
        // Setup for SkinsViewModel
        skinsViewModel = SkinsViewModel()
        skinsViewModel.allSkins = sampleSkins
        skinsViewModel.searchSkins(null)

        // Setup for StickersViewModel
        stickersViewModel = StickersViewModel()
        stickersViewModel.allStickers = sampleStickers
        stickersViewModel.searchStickers(null)

        // Setup for CratesViewModel
        cratesViewModel = CratesViewModel()
    }


    // ========== SkinsViewModel Tests ==========

    private val sampleSkins = listOf(
        Skin("skin-1", "AWP | Dragon Lore", "description", mockWeapon, mockCategory, mockPattern, 0.0f, 1.0f, mockRarity, false, "img_url"),
        Skin("skin-2", "AK-47 | Redline", "description", mockWeapon, mockCategory, mockPattern, 0.0f, 1.0f, mockRarity, false, "img_url"),
        Skin("skin-3", "M4A4 | Howl", "description", mockWeapon, mockCategory, mockPattern, 0.0f, 1.0f, mockRarity, false, "img_url"),
        Skin("skin-4", "AWP | Gungnir", "description", mockWeapon, mockCategory, mockPattern, 0.0f, 1.0f, mockRarity, false, "img_url"),
        Skin("skin-5", "Desert Eagle | Dragon's Breath", "description", mockWeapon, mockCategory, mockPattern, 0.0f, 1.0f, mockRarity, false, "img_url")
    )

    @Test
    fun `searchSkins should filter the list correctly`() {
        skinsViewModel.searchSkins("Dragon")
        val filteredSkins = skinsViewModel.skins.getOrAwaitValue()
        assertEquals(2, filteredSkins?.size)
        assertEquals("AWP | Dragon Lore", filteredSkins?.get(0)?.name)
        assertEquals("Desert Eagle | Dragon's Breath", filteredSkins?.get(1)?.name)
    }

    @Test
    fun `searchSkins should be case-insensitive`() {
        skinsViewModel.searchSkins("dragon")
        val filteredSkins = skinsViewModel.skins.getOrAwaitValue()
        assertEquals(2, filteredSkins?.size)
    }

    @Test
    fun `searchSkins with empty query should return the full list`() {
        skinsViewModel.searchSkins("Dragon") // Pre-filter to ensure reset works
        skinsViewModel.searchSkins("")
        val fullList = skinsViewModel.skins.getOrAwaitValue()
        assertEquals(5, fullList?.size)
    }

    @Test
    fun `searchSkins with null query should return the full list`() {
        skinsViewModel.searchSkins("Redline") // Pre-filter to ensure reset works
        skinsViewModel.searchSkins(null)
        val fullList = skinsViewModel.skins.getOrAwaitValue()
        assertEquals(5, fullList?.size)
    }

    @Test
    fun `searchSkins with no matching results should return an empty list`() {
        skinsViewModel.searchSkins("NonExistentSkinName")
        val result = skinsViewModel.skins.getOrAwaitValue()
        assertEquals(0, result?.size)
    }

    // ========== CratesViewModel Tests ==========

    private fun makeCrate(id: String, name: String): Crate {
        val mockRarity = Rarity("rarity_common", "Common", "#ffffff")
        return Crate(
            id = id, name = name, description = null, type = null, first_sale_date = null,
            contains = listOf(ContainedItem("item1", "Item 1", mockRarity, "image_url")),
            contains_rare = null, image = "", loot_list = null
        )
    }

    private fun setPrivateAllCrates(viewModel: CratesViewModel, list: List<Crate>) {
        val field: Field = viewModel.javaClass.getDeclaredField("allCrates")
        field.isAccessible = true
        field.set(viewModel, list)
    }

    @Test
    fun searchCrates_nullQuery_returnsAll() {
        val c1 = makeCrate("1", "Alpha")
        val c2 = makeCrate("2", "Beta")
        setPrivateAllCrates(cratesViewModel, listOf(c1, c2))
        cratesViewModel.searchCrates(null)
        val result = cratesViewModel.crates.getOrAwaitValue()
        assertEquals(2, result?.size)
        assertEquals(listOf(c1, c2), result)
    }

    @Test
    fun searchCrates_filtersByName_ignoreCase() {
        val c1 = makeCrate("1", "Weapon Crate")
        val c2 = makeCrate("2", "Skin Crate")
        val c3 = makeCrate("3", "Special Weapon")
        setPrivateAllCrates(cratesViewModel, listOf(c1, c2, c3))
        cratesViewModel.searchCrates("weapon")
        val result = cratesViewModel.crates.getOrAwaitValue()
        assertEquals(2, result?.size)
        assertEquals(listOf(c1, c3), result)
    }

    // ========== Category Tests ==========

    @Test
    fun category_creation_withAllProperties_success() {
        val category = Category(id = "cat1", name = "Rifle")
        assertEquals("cat1", category.id)
        assertEquals("Rifle", category.name)
    }

    @Test
    fun category_equality_comparison_success() {
        val category1 = Category("1", "Rifle")
        val category2 = Category("1", "Rifle")
        val category3 = Category("2", "Pistol")
        assertEquals(category1, category2)
        assertNotEquals(category1, category3)
    }

    // ========== StickersViewModel Tests ==========

    private val sampleStickers = listOf(
        Sticker(id = "sticker-1", name = "Sticker Alpha", description = "desc", rarity = mockRarity, crates = listOf(mockCrateForStickers), tournament_event = null, tournament_team = null, type = "Default", market_hash_name = "market_1", effect = null, image = "img_1"),
        Sticker(id = "sticker-2", name = "Team Bravo Sticker", description = "desc", rarity = mockRarity, crates = listOf(mockCrateForStickers), tournament_event = "PGL Major 2024", tournament_team = "Team Bravo", type = "Tournament", market_hash_name = "market_2", effect = null, image = "img_2"),
        Sticker(id = "sticker-3", name = "Player Charlie Signature", description = "desc", rarity = mockRarity, crates = listOf(mockCrateForStickers), tournament_event = "PGL Major 2024", tournament_team = "Team Charlie", type = "Tournament", market_hash_name = "market_3", effect = null, image = "img_3"),
        Sticker(id = "sticker-4", name = "Sticker Delta", description = "desc", rarity = mockRarity, crates = listOf(mockCrateForStickers), tournament_event = null, tournament_team = null, type = "Default", market_hash_name = "market_4", effect = null, image = "img_4"),
        Sticker(id = "sticker-5", name = "Team Bravo Holo", description = "desc", rarity = mockRarity, crates = listOf(mockCrateForStickers), tournament_event = "ESL One Cologne 2023", tournament_team = "Team Bravo", type = "Tournament", market_hash_name = "market_5", effect = "Holo", image = "img_5")
    )

    @Test
    fun `searchStickers should filter by name correctly`() {
        val nameOnlyStickers = listOf(
            Sticker(id = "s1", name = "SearchMe Name", rarity = mockRarity, description = "...", crates = emptyList(), tournament_event = "Other", tournament_team = "Other", type = "...", market_hash_name = "...", effect = null, image = "..."),
            Sticker(id = "s2", name = "Another SearchMe", rarity = mockRarity, description = "...", crates = emptyList(), tournament_event = "Other", tournament_team = "Other", type = "...", market_hash_name = "...", effect = null, image = "..."),
            Sticker(id = "s3", name = "Irrelevant", rarity = mockRarity, description = "...", crates = emptyList(), tournament_event = "Other", tournament_team = "Other", type = "...", market_hash_name = "...", effect = null, image = "...")
        )
        stickersViewModel.allStickers = nameOnlyStickers

        stickersViewModel.searchStickers("SearchMe")
        val filteredList = stickersViewModel.stickers.getOrAwaitValue()
        assertEquals(2, filteredList?.size)
    }

    @Test
    fun `searchStickers should filter by team correctly`() {
        stickersViewModel.searchStickers("Bravo")
        val filteredList = stickersViewModel.stickers.getOrAwaitValue()
        assertEquals(2, filteredList?.size)
        assertEquals("Team Bravo Sticker", filteredList?.get(0)?.name)
        assertEquals("Team Bravo Holo", filteredList?.get(1)?.name)
    }

    @Test
    fun `searchStickers should filter by event correctly`() {
        stickersViewModel.searchStickers("PGL Major")
        val filteredList = stickersViewModel.stickers.getOrAwaitValue()
        assertEquals(2, filteredList?.size)
        assertEquals("Team Bravo Sticker", filteredList?.get(0)?.name)
        assertEquals("Player Charlie Signature", filteredList?.get(1)?.name)
    }

    @Test
    fun `searchStickers should find matches across different fields`() {
        val testStickers = listOf(
            Sticker(id = "s1", name = "MatchTerm Sticker", rarity = mockRarity, description = "...", crates = emptyList(), tournament_event = "Event A", tournament_team = "Team X", type = "...", market_hash_name = "...", effect = null, image = "..."),
            Sticker(id = "s2", name = "Another Sticker", rarity = mockRarity, description = "...", crates = emptyList(), tournament_event = "Event B", tournament_team = "Team MatchTerm", type = "...", market_hash_name = "...", effect = null, image = "..."),
            Sticker(id = "s3", name = "Third Sticker", rarity = mockRarity, description = "...", crates = emptyList(), tournament_event = "The MatchTerm Event", tournament_team = "Team Z", type = "...", market_hash_name = "...", effect = null, image = "..."),
            Sticker(id = "s4", name = "Irrelevant", rarity = mockRarity, description = "...", crates = emptyList(), tournament_event = "Event C", tournament_team = "Team W", type = "...", market_hash_name = "...", effect = null, image = "...")
        )
        stickersViewModel.allStickers = testStickers

        stickersViewModel.searchStickers("MatchTerm")

        val result = stickersViewModel.stickers.getOrAwaitValue()
        assertEquals("Should find matches in name, team, and event fields", 3, result?.size)
        assertTrue(result?.any { it.id == "s1" } ?: false)
        assertTrue(result?.any { it.id == "s2" } ?: false)
        assertTrue(result?.any { it.id == "s3" } ?: false)
    }

    @Test
    fun `searchStickers should be case-insensitive`() {
        stickersViewModel.searchStickers("bravo")
        val filteredList = stickersViewModel.stickers.getOrAwaitValue()
        assertEquals(2, filteredList?.size)
    }

    @Test
    fun `searchStickers with empty query should return the full list`() {
        stickersViewModel.searchStickers("Bravo") // Pre-filter to ensure reset works
        stickersViewModel.searchStickers("")
        val filteredList = stickersViewModel.stickers.getOrAwaitValue()
        assertEquals(5, filteredList?.size)
    }

    @Test
    fun `searchStickers with null query should return the full list`() {
        stickersViewModel.searchStickers("Bravo") // Pre-filter to ensure reset works
        stickersViewModel.searchStickers(null)
        val filteredList = stickersViewModel.stickers.getOrAwaitValue()
        assertEquals(5, filteredList?.size)
    }

    @Test
    fun `searchStickers with no matching results should return an empty list`() {
        stickersViewModel.searchStickers("NonExistentSticker")
        val result = stickersViewModel.stickers.getOrAwaitValue()
        assertEquals(0, result?.size)
    }

    // ========== LiveData Helper ==========

    private fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T? {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)
        if (!latch.await(time, timeUnit)) {
            this.removeObserver(observer)
            throw TimeoutException("LiveData value was never set.")
        }
        @Suppress("UNCHECKED_CAST")
        return data
    }
}