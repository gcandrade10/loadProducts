package com.ger.loadproducts.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.ger.loadproducts.domain.Product
import com.ger.loadproducts.ui.theme.LoadProductsTheme
import com.ger.loadproducts.viewmodel.ProductState
import com.ger.loadproducts.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            val productState = viewModel.productState.observeAsState()

            LoadProductsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductsScreen(productState.value!!, innerPadding)
                }
            }
        }
    }
}

