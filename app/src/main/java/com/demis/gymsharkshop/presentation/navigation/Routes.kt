package com.demis.gymsharkshop.presentation.navigation

import kotlinx.serialization.Serializable

/** Type-safe Navigation-Compose route destinations. */
@Serializable
object Splash

@Serializable
object ProductList

@Serializable
data class ProductDetail(val id: Long)
