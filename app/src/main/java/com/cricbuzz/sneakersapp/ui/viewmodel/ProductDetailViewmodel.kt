package com.cricbuzz.sneakersapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cricbuzz.sneakersapp.domain.usecases.IAddToCartUseCase
import com.cricbuzz.sneakersapp.domain.usecases.IProductDetailUseCase
import com.cricbuzz.sneakersapp.ui.model.SneakerUIModel
import com.cricbuzz.sneakersapp.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Product detail viewmodel
 * This viewmodel is associated with ProductDetailsFragment
 * @property useCase
 * @property addToCartUseCase
 * @constructor Create empty Product detail viewmodel
 */
@HiltViewModel
class ProductDetailViewmodel @Inject constructor(private val useCase: IProductDetailUseCase, private val addToCartUseCase: IAddToCartUseCase): ViewModel() {
    private val _sneaker = MutableStateFlow<Resource<SneakerUIModel?>>(Resource.loading(true))
    val sneaker = _sneaker.asStateFlow()

    private val _addItemToCart = MutableStateFlow<Resource<Long>>(Resource.loading(true))
    val addItemToCart = _addItemToCart.asStateFlow()

    /**
     * Fetch product details
     * This function fetch the details of product based on id
     * @param id
     */
    fun fetchProductDetails(id: String) {
        viewModelScope.launch {
            useCase.fetchSneakers(id).collect {
                _sneaker.emit(it)
            }
        }
    }

    /**
     * Add to cart
     * Adds the item to cart
     * @param product
     */
    fun addToCart(product: SneakerUIModel) {
        viewModelScope.launch {
            addToCartUseCase.addSneakerToCart(product).collect {
                _addItemToCart.emit(it)
            }
        }
    }
}