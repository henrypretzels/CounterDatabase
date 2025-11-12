package com.example.counterdatabase.ui.stickers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.counterdatabase.data.Crate
import com.example.counterdatabase.data.Rarity
import com.example.counterdatabase.data.Sticker
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StickersViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: StickersViewModel

    private val mockRarity = Rarity("rarity_common", "Common", "#ffffff")

    // Correctly structured mock object for the Crate
    private val mockCrate = Crate(
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

    private val sampleStickers = listOf(
        Sticker(
            id = "sticker-1", name = "Sticker Alpha", description = "desc", rarity = mockRarity,
            crates = listOf(mockCrate), tournament_event = null, tournament_team = null, type = "Default",
            market_hash_name = "market_1", effect = null, image = "img_1"
        ),
        Sticker(
            id = "sticker-2", name = "Team Bravo Sticker", description = "desc", rarity = mockRarity,
            crates = listOf(mockCrate), tournament_event = "PGL Major 2024", tournament_team = "Team Bravo", type = "Tournament",
            market_hash_name = "market_2", effect = null, image = "img_2"
        ),
        Sticker(
            id = "sticker-3", name = "Player Charlie Signature", description = "desc", rarity = mockRarity,
            crates = listOf(mockCrate), tournament_event = "PGL Major 2024", tournament_team = "Team Charlie", type = "Tournament",
            market_hash_name = "market_3", effect = null, image = "img_3"
        ),
        Sticker(
            id = "sticker-4", name = "Sticker Delta", description = "desc", rarity = mockRarity,
            crates = listOf(mockCrate), tournament_event = null, tournament_team = null, type = "Default",
            market_hash_name = "market_4", effect = null, image = "img_4"
        ),
        Sticker(
            id = "sticker-5", name = "Team Bravo Holo", description = "desc", rarity = mockRarity,
            crates = listOf(mockCrate), tournament_event = "ESL One Cologne 2023", tournament_team = "Team Bravo", type = "Tournament",
            market_hash_name = "market_5", effect = "Holo", image = "img_5"
        )
    )

    @Before
    fun setUp() {
        viewModel = StickersViewModel()
        viewModel.allStickers = sampleStickers
        viewModel.searchStickers(null)
    }

    @Test
    fun `searchStickers should filter by name correctly`() {
        viewModel.searchStickers("Sticker")
        val filteredList = viewModel.stickers.value
        assertEquals(2, filteredList?.size)
        assertEquals("Sticker Alpha", filteredList?.get(0)?.name)
        assertEquals("Sticker Delta", filteredList?.get(1)?.name)
    }

    @Test
    fun `searchStickers should filter by team correctly`() {
        viewModel.searchStickers("Bravo")
        val filteredList = viewModel.stickers.value
        assertEquals(2, filteredList?.size)
        assertEquals("Team Bravo Sticker", filteredList?.get(0)?.name)
        assertEquals("Team Bravo Holo", filteredList?.get(1)?.name)
    }

    @Test
    fun `searchStickers should filter by event correctly`() {
        viewModel.searchStickers("PGL Major")
        val filteredList = viewModel.stickers.value
        assertEquals(2, filteredList?.size)
        assertEquals("Team Bravo Sticker", filteredList?.get(0)?.name)
        assertEquals("Player Charlie Signature", filteredList?.get(1)?.name)
    }

    @Test
    fun `searchStickers should be case-insensitive`() {
        viewModel.searchStickers("bravo")
        val filteredList = viewModel.stickers.value
        assertEquals(2, filteredList?.size)
    }

    @Test
    fun `searchStickers with empty query should return the full list`() {
        viewModel.searchStickers("Bravo")
        assertEquals(2, viewModel.stickers.value?.size)

        viewModel.searchStickers("")
        assertEquals(5, viewModel.stickers.value?.size)
    }

    @Test
    fun `searchStickers with null query should return the full list`() {
        viewModel.searchStickers("Bravo")
        assertEquals(2, viewModel.stickers.value?.size)

        viewModel.searchStickers(null)
        assertEquals(5, viewModel.stickers.value?.size)
    }

    @Test
    fun `searchStickers with no matching results should return an empty list`() {
        viewModel.searchStickers("NonExistentSticker")
        val result = viewModel.stickers.value
        assertEquals(0, result?.size)
    }
}
