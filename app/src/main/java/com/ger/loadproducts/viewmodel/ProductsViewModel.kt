package com.ger.loadproducts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _productState = MutableLiveData(
        ProductState(
            products = listOf(
                Product("Toalla", "Toalla de algodón 80 x 120 cm", 299.9)
            )
        )
    )
    val productState: LiveData<ProductState> = _productState

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("¡Error no capturado!: ${exception.message}")
    }

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                val nuevaLista = productRepository.getProducts()
                _productState.postValue(_productState.value?.copy(products = nuevaLista, isLoading = false))
            } catch (e: Exception) {
                _productState.postValue(_productState.value?.copy(error = e, isLoading = false))
            }
        }
    }
}