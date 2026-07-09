package com.demis.gymsharkshop.data.remote

import retrofit2.http.GET

interface ProductApi {

    @GET("training/mock-product-responses/algolia-example-payload.json")
    suspend fun getProducts(): ProductResponseDto
}