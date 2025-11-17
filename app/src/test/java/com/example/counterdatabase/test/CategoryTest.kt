package com.example.counterdatabase.data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class CategoryTest {

    @Test
    fun category_creation_withAllProperties_success() {
        val category = Category(
            id = "cat1",
            name = "Rifle"
        )

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
}

