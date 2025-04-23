package com.ger.loadproducts.data

import com.ger.loadproducts.domain.Product

class ProductRepository(private val apiService: ApiService) {
    suspend fun getProducts(): List<Product> {
        return apiService.getProducts().map { remoteProduct ->
            remoteProduct.toProduct()
        }
    }
}

fun RemoteProduct.toProduct() = Product(name, description, price)