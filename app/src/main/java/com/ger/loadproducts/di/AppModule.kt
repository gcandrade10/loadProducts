package com.ger.loadproducts.di

import com.ger.loadproducts.data.ApiService
import com.ger.loadproducts.data.ProductRepository
import com.ger.loadproducts.data.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.instance
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): ProductRepository {
        return ProductRepository(apiService)
    }
}