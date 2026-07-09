package com.demis.gymsharkshop.presentation.list

import com.demis.gymsharkshop.domain.ErrorType
import com.demis.gymsharkshop.presentation.model.ProductUi

sealed interface ProductListUiState {
    data object Loading : ProductListUiState
    data class Content(val items: List<ProductUi>) : ProductListUiState
    data object Empty : ProductListUiState
    data class Error(val message: String) : ProductListUiState
}

/** Maps a domain [ErrorType] to a user-facing message. */
fun ErrorType.toUserMessage(): String = when (this) {
    ErrorType.NO_INTERNET -> "No internet connection.\nCheck your network and try again."
    ErrorType.SERVER -> "Something went wrong on our end.\nPlease try again."
    ErrorType.SERIALIZATION -> "We couldn't read the products.\nPlease try again."
    ErrorType.UNKNOWN -> "Something went wrong.\nPlease try again."
}
