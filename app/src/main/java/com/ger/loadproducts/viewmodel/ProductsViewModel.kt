package com.ger.loadproducts.viewmodel

import androidx.lifecycle.ViewModel
import com.ger.loadproducts.data.ProductRepository
import com.ger.loadproducts.domain.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    init {
        // TODO
    }
}