package com.demis.gymsharkshop.data.mapper

import com.demis.gymsharkshop.data.remote.ProductDto
import com.demis.gymsharkshop.domain.Product
import com.demis.gymsharkshop.domain.ProductLabel

fun ProductDto.toProduct() = Product(
    id = id,
    title = title,
    priceMinor = price,
    colour = colour,
    imageUrl = featuredMedia.photoUrl,
    images = media.map { it.photoUrl },
    labels = labels.orEmpty()
        .map(ProductLabel::fromRaw)
        .filter { it != ProductLabel.UNKNOWN },
    descriptionHtml = description,
    type = type,
)