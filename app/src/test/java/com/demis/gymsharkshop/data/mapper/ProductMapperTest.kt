package com.demis.gymsharkshop.data.mapper

import com.demis.gymsharkshop.data.remote.MediaDto
import com.demis.gymsharkshop.data.remote.ProductDto
import com.demis.gymsharkshop.data.testUtil.Util.createFakesDto
import com.demis.gymsharkshop.domain.ProductLabel
import org.junit.Assert.*
import org.junit.Test

class ProductMapperTest {

    @Test
    fun `ProductDto to Product maps correctly`() {
        val dto = createFakesDto(labels = listOf("going-fast", "new"))
        val domain = dto.toProduct()
        assertEquals(dto.id, domain.id)
        assertEquals(dto.title, domain.title)
        assertEquals(dto.price, domain.priceMinor)
        assertEquals(dto.colour, domain.colour)
        assertEquals(dto.featuredMedia.photoUrl, domain.imageUrl)
        assertEquals(2, domain.labels.size)
        assertEquals(dto.media.map { it.photoUrl }, domain.images)
        assertEquals(dto.description, domain.descriptionHtml)
        assertEquals(dto.type, domain.type)
        assertEquals(ProductLabel.NEW, domain.labels[1])
        assertEquals(ProductLabel.GOING_FAST, domain.labels[0])
    }

    @Test
    fun `toProduct maps labels with mixed casing correctly`() {
        val dto = createFakesDto(labels = listOf("NEW", "Going-Fast"))
        val domain = dto.toProduct()

        assertEquals(2, domain.labels.size)
        assertEquals(ProductLabel.NEW, domain.labels[0])
        assertEquals(ProductLabel.GOING_FAST, domain.labels[1])
    }

    @Test
    fun `toProduct drops unknown labels`() {
        val dto = createFakesDto(labels = listOf("unknown-label", "new"))
        val domain = dto.toProduct()

        assertEquals(1, domain.labels.size)
        assertEquals(ProductLabel.NEW, domain.labels[0])
    }

    @Test
    fun `ProductDto to Product maps correctly with null labels`() {
        val dto = createFakesDto(labels = null)
        val domain = dto.toProduct()
        assertTrue(domain.labels.isEmpty())
    }

}