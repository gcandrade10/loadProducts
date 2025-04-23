package com.ger.loadproducts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ger.loadproducts.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel
@Inject
constructor(private val productRepository: ProductRepository) : ViewModel() {

    private val _productState =
        MutableStateFlow(ProductState())
    val productState = _productState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val productsFromRemote = productRepository.getProducts()
                _productState.update { it.copy(products = productsFromRemote, isLoading = false) }
            } catch (e: Exception) {
                _productState.update { it.copy(error = e) }
                e.printStackTrace()
            }
        }
    }
}