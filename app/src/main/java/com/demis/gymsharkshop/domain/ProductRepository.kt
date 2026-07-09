package com.demis.gymsharkshop.domain

interface ProductRepository {

    suspend fun getProducts(): ProductResult

    fun getCachedProductById(id: Long): Product?
}