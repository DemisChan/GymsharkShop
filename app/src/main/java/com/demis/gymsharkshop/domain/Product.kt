package com.demis.gymsharkshop.domain

data class Product(
    val id: Long,
    val title: String,
    val priceMinor: Int,
    val colour: String,
    val imageUrl: String,
    val images: List<String>,
    val labels: List<ProductLabel>,
    val descriptionHtml: String,
    val type: String,
)
