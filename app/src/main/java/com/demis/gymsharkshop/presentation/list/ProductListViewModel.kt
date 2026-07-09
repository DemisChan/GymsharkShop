package com.demis.gymsharkshop.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demis.gymsharkshop.domain.ProductRepository
import com.demis.gymsharkshop.domain.ProductResult
import com.demis.gymsharkshop.presentation.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<ProductListUiState>(ProductListUiState.Loading)
    val state: StateFlow<ProductListUiState> = _state.asStateFlow()

    init {
        load()
    }

    fun load() {
        _state.value = ProductListUiState.Loading
        viewModelScope.launch {
            _state.value = when (val result = repository.getProducts()) {
                is ProductResult.Success ->
                    if (result.products.isEmpty()) {
                        ProductListUiState.Empty
                    } else {
                        ProductListUiState.Content(result.products.map { it.toUi() })
                    }

                is ProductResult.Error -> ProductListUiState.Error(result.type.toUserMessage())
            }
        }
    }
}
