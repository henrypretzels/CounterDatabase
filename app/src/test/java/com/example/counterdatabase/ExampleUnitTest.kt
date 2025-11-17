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
import com.example.counterdatabase.data.Weapon
import com.example.counterdatabase.ui.crates.CratesViewModel
import com.example.counterdatabase.ui.skins.SkinsViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.lang.reflect.Field
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


class ExampleUnitTest {

    // força execução síncrona do LiveData em testes
    @get:Rule
    val instantExecutorRule: TestRule = InstantTaskExecutorRule()

    // helper rápido: captura valor de LiveData
    private fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T? { // Return type is nullable
        var data: T? = null
        val latch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(value: T) { // Corrected signature
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            this.removeObserver(observer)
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data
    }

    // ========== SkinsViewModel Tests ==========
    
    private lateinit var skinsViewModel: SkinsViewModel

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
        skinsViewModel = SkinsViewModel()
        skinsViewModel.allSkins = sampleSkins
        skinsViewModel.searchSkins(null)
    }

    @Test
    fun `searchSkins should filter the list correctly`() {
        skinsViewModel.searchSkins("Dragon")

        val filteredSkins = skinsViewModel.skins.value
        assertEquals(2, filteredSkins?.size)
        assertEquals("AWP | Dragon Lore", filteredSkins?.get(0)?.name)
        assertEquals("Desert Eagle | Dragon's Breath", filteredSkins?.get(1)?.name)
    }

    @Test
    fun `searchSkins should be case-insensitive`() {
        skinsViewModel.searchSkins("dragon")

        val filteredSkins = skinsViewModel.skins.value
        assertEquals(2, filteredSkins?.size)
    }

    @Test
    fun `searchSkins with empty query should return the full list`() {
        skinsViewModel.searchSkins("Dragon")
        assertEquals(2, skinsViewModel.skins.value?.size)

        skinsViewModel.searchSkins("")

        val fullList = skinsViewModel.skins.value
        assertEquals(5, fullList?.size)
    }

    @Test
    fun `searchSkins with null query should return the full list`() {
        skinsViewModel.searchSkins("Redline")
        assertEquals(1, skinsViewModel.skins.value?.size)

        skinsViewModel.searchSkins(null)

        val fullList = skinsViewModel.skins.value
        assertEquals(5, fullList?.size)
    }

    @Test
    fun `searchSkins with no matching results should return an empty list`() {
        skinsViewModel.searchSkins("NonExistentSkinName")

        val result = skinsViewModel.skins.value
        assertEquals(0, result?.size)
    }

    // ========== CratesViewModel Tests ==========

    // cria um Crate mínimo
    private fun makeCrate(id: String, name: String): Crate {
        // Mock rarity needed for ContainedItem
        val mockRarity = Rarity("rarity_common", "Common", "#ffffff")
        return Crate(
            id = id,
            name = name,
            description = null,
            type = null,
            first_sale_date = null,
            // Correctly create ContainedItem with all required parameters
            contains = listOf(ContainedItem("item1", "Item 1", mockRarity, "image_url")),
            contains_rare = null,
            image = "",
            loot_list = null
        )
    }

    // usa reflection para setar a lista privada allCrates do ViewModel (sem alterar o código da app)
    private fun setPrivateAllCrates(viewModel: CratesViewModel, list: List<Crate>) {
        val field: Field = viewModel.javaClass.getDeclaredField("allCrates")
        field.isAccessible = true
        field.set(viewModel, list)
    }

    // query nula deve retornar todas as crates
    @Test
    fun searchCrates_nullQuery_returnsAll() {
        val vm = CratesViewModel()
        val c1 = makeCrate("1", "Alpha")
        val c2 = makeCrate("2", "Beta")
        setPrivateAllCrates(vm, listOf(c1, c2))

        vm.searchCrates(null)

        val result = vm.crates.getOrAwaitValue()
        assertEquals(2, result?.size)
        assertEquals(listOf(c1, c2), result)
    }

    // busca filtra por nome
    @Test
    fun searchCrates_filtersByName_ignoreCase() {
        val vm = CratesViewModel()
        val c1 = makeCrate("1", "Weapon Crate")
        val c2 = makeCrate("2", "Skin Crate")
        val c3 = makeCrate("3", "Special Weapon")
        setPrivateAllCrates(vm, listOf(c1, c2, c3))

        vm.searchCrates("weapon")

        val result = vm.crates.getOrAwaitValue()
        assertEquals(2, result?.size)
        assertEquals(listOf(c1, c3), result)
    }
}
