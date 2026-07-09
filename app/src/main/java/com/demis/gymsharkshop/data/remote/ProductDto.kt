package com.demis.gymsharkshop.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Long,
    val sku: String,
    val title: String,
    val description: String,
    val gender: List<String>,
    val price: Int,
    val handle: String,
    val labels: List<String>? = null,
    val type: String,
    val colour: String,
    val featuredMedia: MediaDto,
    val media: List<MediaDto>,
)
