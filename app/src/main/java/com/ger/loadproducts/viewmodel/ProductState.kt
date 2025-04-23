package com.ger.loadproducts.viewmodel

import com.ger.loadproducts.domain.Product

data class ProductState(
    val isLoading: Boolean = true,
    val products: List<Product> = emptyList(),
//    val products: List<Product> = listOf(
//        Product(
//            "Toalla",
//            "Toalla de algodon 80 x 120 cm",
//            299.9
//        )
//    ),
    val error: Exception? = null
)