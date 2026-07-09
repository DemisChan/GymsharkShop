package com.demis.gymsharkshop.presentation.list

import com.demis.gymsharkshop.domain.ErrorType
import com.demis.gymsharkshop.domain.Product
import com.demis.gymsharkshop.domain.ProductRepository
import com.demis.gymsharkshop.domain.ProductResult
import com.demis.gymsharkshop.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: ProductRepository = mockk()

    @Test
    fun `success with products emits Content with mapped items`() = runTest {
        coEvery { repository.getProducts() } returns ProductResult.Success(listOf(product(1), product(2)))

        val state = ProductListViewModel(repository).state.value

        assertTrue(state is ProductListUiState.Content)
        val content = state as ProductListUiState.Content
        assertEquals(2, content.items.size)
        assertEquals("£10.00", content.items.first().priceLabel)
    }

    @Test
    fun `success with empty list emits Empty`() = runTest {
        coEvery { repository.getProducts() } returns ProductResult.Success(emptyList())

        val state = ProductListViewModel(repository).state.value

        assertEquals(ProductListUiState.Empty, state)
    }

    @Test
    fun `error emits Error with mapped message`() = runTest {
        coEvery { repository.getProducts() } returns ProductResult.Error(ErrorType.NO_INTERNET)

        val state = ProductListViewModel(repository).state.value

        assertTrue(state is ProductListUiState.Error)
        assertEquals(ErrorType.NO_INTERNET.toUserMessage(), (state as ProductListUiState.Error).message)
    }

    private fun product(id: Long) = Product(
        id = id,
        title = "Tee $id",
        priceMinor = 1000,
        colour = "Black",
        imageUrl = "https://img/$id.jpg",
        images = emptyList(),
        labels = emptyList(),
        descriptionHtml = "",
        type = "tshirt",
    )
}