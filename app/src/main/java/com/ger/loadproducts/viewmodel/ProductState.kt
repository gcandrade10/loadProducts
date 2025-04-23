package com.ger.loadproducts.viewmodel

import com.ger.loadproducts.domain.Product

data class ProductState(
    val isLoading: Boolean = true,
    val products: List<Product> = emptyList(),
    val error: Exception? = null
)