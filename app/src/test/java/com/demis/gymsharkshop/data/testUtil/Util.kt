package com.demis.gymsharkshop.data.testUtil

import com.demis.gymsharkshop.data.remote.MediaDto
import com.demis.gymsharkshop.data.remote.ProductDto

object Util {

    internal fun createFakesDto(labels: List<String>?): ProductDto {
        val featuredMedia = MediaDto(
            photoUrl = "https://url.com/img.jpg",
            height = 100,
            width = 100,
            photoId = 1L,
            productId = 1L,
            position = 1
        )
        return ProductDto(
            id = 1L,
            title = "Test Product",
            price = 10000,
            colour = "Black",
            featuredMedia = featuredMedia,
            media = emptyList(),
            labels = labels,
            description = "Description",
            type = "Shirt",
            gender = emptyList(),
            handle = "test-product",
            sku = "SKU123"
        )
    }

    internal fun createFakesDto(id: Long): ProductDto {
        return ProductDto(
            id = id,
            title = "Test Product",
            price = 2500,
            colour = "Black",
            featuredMedia = MediaDto(
                height = 100,
                width = 100,
                photoId = 1L,
                productId = id,
                position = 1,
                photoUrl = "https://url.com/img.jpg"
            ),
            media = emptyList(),
            labels = emptyList(),
            description = "Description",
            type = "Shirt",
            sku = "SKU123",
            handle = "test-product",
            gender = listOf("unisex")
        )
    }
}
