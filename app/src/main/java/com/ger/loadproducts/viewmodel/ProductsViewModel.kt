package com.ger.loadproducts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ger.loadproducts.data.ProductRepository
import com.ger.loadproducts.domain.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel
@Inject
constructor(private val productRepository: ProductRepository) : ViewModel() {

    private val _productState = MutableStateFlow(
        ProductState(
            products = listOf(
                Product("Toalla", "Toalla de algodon 80 x 120 cm", 299.9)
            )
        )
    )
    val productState = _productState.asStateFlow()

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("¡Error no capturado!: ${exception.message}")
    }

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                val nuevaLista = productRepository.getProducts()
                _productState.update {
                    it.copy(products = nuevaLista, isLoading = false)
                }
            } catch (e: Exception) {
                _productState.update {
                    it.copy(isLoading = false, error = e)
                }
            }
        }
    }
}