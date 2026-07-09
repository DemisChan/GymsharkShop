package com.demis.gymsharkshop.data.repository

import com.demis.gymsharkshop.data.remote.ProductApi
import com.demis.gymsharkshop.data.remote.ProductResponseDto
import com.demis.gymsharkshop.data.testUtil.Util.createFakesDto
import com.demis.gymsharkshop.domain.ErrorType
import com.demis.gymsharkshop.domain.ProductResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.SerializationException
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ProductRepositoryImplTest {
    private val api: ProductApi = mockk()
    private val repository = ProductRepositoryImpl(api)

    @Test
    fun `getProducts success caches items and returns success`() = runTest {
        val dto = createFakesDto(id = 42L)
        val response = ProductResponseDto(hits = listOf(dto, dto.copy(id = 43L)))
        coEvery { api.getProducts() } returns response

        val result = repository.getProducts()
        assertTrue(result is ProductResult.Success)

        val successData = (result as ProductResult.Success).products
        assertEquals(2, successData.size)
        assertEquals(42L, successData[0].id)
        assertEquals(43L, successData[1].id)

        val cached = repository.getCachedProductById(42L)
        assertEquals(42L, cached?.id)
    }

    @Test
    fun `getProducts replaces cache on refetch, dropping stale ids`() = runTest {
        coEvery { api.getProducts() } returns ProductResponseDto(hits = listOf(createFakesDto(id = 42L)))
        repository.getProducts()
        assertNotNull(repository.getCachedProductById(42L))

        coEvery { api.getProducts() } returns ProductResponseDto(hits = listOf(createFakesDto(id = 50L)))
        repository.getProducts()

        assertNull(repository.getCachedProductById(42L))
        assertNotNull(repository.getCachedProductById(50L))
    }

    @Test
    fun `getProducts failure with IOException returns NO_INTERNET`() = runTest {
        coEvery { api.getProducts() } throws IOException("No connection")

        val result = repository.getProducts()

        assertTrue(result is ProductResult.Error)
        assertEquals(ErrorType.NO_INTERNET, (result as ProductResult.Error).type)
        assertNull(repository.getCachedProductById(42L))
    }

    @Test
    fun `getProducts failure with HttpException returns SERVER`() = runTest {
        val errorResponse = Response.error<ProductResponseDto>(
            500,
            "Internal Server Error".toResponseBody(null)
        )
        coEvery { api.getProducts() } throws HttpException(errorResponse)

        val result = repository.getProducts()

        assertTrue(result is ProductResult.Error)
        assertEquals(ErrorType.SERVER, (result as ProductResult.Error).type)
    }

    @Test
    fun `getProducts failure with SerializationException returns SERIALIZATION`() = runTest {
        coEvery { api.getProducts() } throws SerializationException("Malformed JSON")

        val result = repository.getProducts()

        assertTrue(result is ProductResult.Error)
        assertEquals(ErrorType.SERIALIZATION, (result as ProductResult.Error).type)
    }

    @Test
    fun `getProducts failure with CancellationException rethrows exception`() = runTest {
        coEvery { api.getProducts() } throws CancellationException("Job cancelled")

        try {
            repository.getProducts()
            assertTrue("Expected CancellationException to be thrown", false)
        } catch (e: CancellationException) {
            assertEquals("Job cancelled", e.message)

        }
    }

    @Test
    fun `unknown exception maps to UNKNOWN error`() = runTest {
        coEvery { api.getProducts() } throws IllegalStateException("Exception")
        val result = repository.getProducts()
        assertTrue(result is ProductResult.Error)
        assertEquals(ErrorType.UNKNOWN, (result as ProductResult.Error).type)
    }
}