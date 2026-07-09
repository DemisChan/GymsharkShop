package com.demis.gymsharkshop.domain

sealed interface ProductResult {
    data class Success(val products: List<Product>) : ProductResult
    data class Error(val type: ErrorType) : ProductResult
}

enum class ErrorType {
    NO_INTERNET,           // IOException
    SERIALIZATION,         // SerializationException
    SERVER,                // HttpException
    UNKNOWN                // Unexpected exceptions
}