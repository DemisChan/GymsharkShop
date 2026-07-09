package com.demis.gymsharkshop.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponseDto(
    val hits: List<ProductDto>,
    )