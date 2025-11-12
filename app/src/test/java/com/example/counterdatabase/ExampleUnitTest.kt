package com.example.counterdatabase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.counterdatabase.data.Crate
import com.example.counterdatabase.data.ContainedItem
import com.example.counterdatabase.ui.crates.CratesViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.lang.reflect.Field
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import java.util.concurrent.atomic.AtomicReference


class ExampleUnitTest {

    // força execução síncrona do LiveData em testes
    @get:Rule
    val instantExecutorRule: TestRule = InstantTaskExecutorRule()

    // helper rápido: captura valor de LiveData
    private fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        val data = AtomicReference<T>()
        val latch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data.set(o)
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)

        if (!latch.await(time, timeUnit)) {
            this.removeObserver(observer)
            throw TimeoutException("LiveData value was never set.")
        }

        return data.get()
    }

    // cria um Crate mínimo
    private fun makeCrate(id: String, name: String): Crate {
        return Crate(
            id = id,
            name = name,
            description = null,
            type = null,
            first_sale_date = null,
            contains = listOf(ContainedItem("item1", "Item 1")),
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
        assertEquals(2, result.size)
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
        assertEquals(2, result.size)
        assertEquals(listOf(c1, c3), result)
    }
}
