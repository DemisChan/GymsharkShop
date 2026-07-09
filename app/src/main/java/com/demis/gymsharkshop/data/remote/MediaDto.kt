package com.demis.gymsharkshop.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaDto(
    val height: Int,
    val width: Int,
    @SerialName("id")
    val photoId: Long,
    @SerialName("product_id")
    val productId: Long,
    val position: Int,
    @SerialName("src")
    val photoUrl: String,
)
