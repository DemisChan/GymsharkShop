package com.demis.gymsharkshop.presentation.detail

import com.demis.gymsharkshop.presentation.model.ProductUi

sealed interface ProductDetailUiState {
    data class Content(val product: ProductUi) : ProductDetailUiState
    data object NotFound : ProductDetailUiState
}
