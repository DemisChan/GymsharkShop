package com.demis.gymsharkshop.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.demis.gymsharkshop.domain.ProductRepository
import com.demis.gymsharkshop.presentation.model.toUi
import com.demis.gymsharkshop.presentation.navigation.ProductDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    repository: ProductRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val productId: Long = savedStateHandle.toRoute<ProductDetail>().id

    // Cache is populated synchronously by the list load; resolving here needs no coroutine.
    val state: ProductDetailUiState =
        repository.getCachedProductById(productId)
            ?.let { ProductDetailUiState.Content(it.toUi()) }
            ?: ProductDetailUiState.NotFound
}
