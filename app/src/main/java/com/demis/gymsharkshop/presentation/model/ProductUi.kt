package com.demis.gymsharkshop.presentation.model

import com.demis.gymsharkshop.domain.Product
import com.demis.gymsharkshop.domain.ProductLabel
import java.util.Locale

/**
 * Display-ready product model. Distinct from the domain [Product] so no composable ever
 * formats money or reaches fields the UI shouldn't render (e.g. domain `type`).
 */
data class ProductUi(
    val id: Long,
    val name: String,
    val priceLabel: String,
    val colour: String,
    val imageUrl: String,
    val images: List<String>,
    val labels: List<ProductLabel>,
    val descriptionHtml: String,
)

fun Product.toUi(): ProductUi = ProductUi(
    id = id,
    name = title,
    priceLabel = formatPrice(priceMinor),
    colour = colour,
    imageUrl = imageUrl,
    images = images,
    labels = labels,
    descriptionHtml = descriptionHtml,
)

/**
 * Formats an Int in minor currency units to a GBP string (payload has no currency field).
 * `%02d` zero-pads the pence so 1005 -> "£10.05" and 1000 -> "£10.00". [Locale.UK] keeps
 * output deterministic regardless of the device/test-machine locale.
 */
fun formatPrice(priceMinor: Int): String =
    String.format(Locale.UK, "£%d.%02d", priceMinor / 100, priceMinor % 100)