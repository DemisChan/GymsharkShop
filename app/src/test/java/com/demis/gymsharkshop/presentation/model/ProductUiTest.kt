package com.demis.gymsharkshop.presentation.model

import com.demis.gymsharkshop.domain.Product
import com.demis.gymsharkshop.domain.ProductLabel
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductUiTest {

    @Test
    fun `formatPrice pads pence to two digits`() {
        assertEquals("£10.00", formatPrice(1000))
        assertEquals("£10.05", formatPrice(1005)) // single-digit pence must zero-pad
        assertEquals("£10.50", formatPrice(1050))
        assertEquals("£9.99", formatPrice(999))
        assertEquals("£0.00", formatPrice(0))
    }

    @Test
    fun `toUi maps domain fields and formats price`() {
        val product = Product(
            id = 42,
            title = "Power Hoodie",
            priceMinor = 6000,
            colour = "Onyx Grey",
            imageUrl = "https://img/a.jpg",
            images = listOf("https://img/a.jpg", "https://img/b.jpg"),
            labels = listOf(ProductLabel.NEW),
            descriptionHtml = "<b>Warm</b>",
            type = "hoodie",
        )

        val ui = product.toUi()

        assertEquals(42, ui.id)
        assertEquals("Power Hoodie", ui.name)
        assertEquals("£60.00", ui.priceLabel)
        assertEquals("Onyx Grey", ui.colour)
        assertEquals("https://img/a.jpg", ui.imageUrl)
        assertEquals(listOf("https://img/a.jpg", "https://img/b.jpg"), ui.images)
        assertEquals(listOf(ProductLabel.NEW), ui.labels)
        assertEquals("<b>Warm</b>", ui.descriptionHtml)
    }
}