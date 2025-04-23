package com.ger.loadproducts.data

import retrofit2.http.GET

interface ApiService {
    @GET("products.json")
    suspend fun getProducts(
    ): List<RemoteProduct>
}