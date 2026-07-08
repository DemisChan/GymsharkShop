package com.demis.gymsharkshop.domain

enum class ProductLabel {
    GOING_FAST, NEW, POPULAR, LIMITED_EDITION, RECYCLED_NYLON, RECYCLED_POLYESTER, UNKNOWN;

    companion object {
        fun fromRaw(value: String): ProductLabel {
            return when (value) {
                "going-fast" -> GOING_FAST
                "new" -> NEW
                "popular" -> POPULAR
                "limited-edition" -> LIMITED_EDITION
                "recycled-nylon" -> RECYCLED_NYLON
                "recycled-polyester" -> RECYCLED_POLYESTER
                else -> UNKNOWN
            }
        }
    }
}