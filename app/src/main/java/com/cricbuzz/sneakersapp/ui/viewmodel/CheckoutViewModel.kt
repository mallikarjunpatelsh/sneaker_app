package com.cricbuzz.sneakersapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.domain.usecases.ICheckoutGetItemsUseCase
import com.cricbuzz.sneakersapp.domain.usecases.IDeleteSneakerFromCartUseCase
import com.cricbuzz.sneakersapp.ui.model.CheckoutModel
import com.cricbuzz.sneakersapp.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Checkout view model
 * This viewmodel is associated with CheckoutFragment
 * @property checkoutAllItemsUC
 * @property deleteSneakersFromCartUseCase
 * @constructor Create empty Checkout view model
 */
@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val checkoutAllItemsUC: ICheckoutGetItemsUseCase,
    private val deleteSneakersFromCartUseCase: IDeleteSneakerFromCartUseCase
) : ViewModel() {
    private val _cartItems = MutableStateFlow<Resource<CheckoutModel?>>(Resource.loading(true))
    val cartItems = _cartItems.asStateFlow()

    private val _deleteItem = MutableStateFlow<Resource<Int?>>(Resource.loading(true))
    val deleteItem = _deleteItem.asStateFlow()

    /**
     * Get all cart items
     * This functions collects all items in cart
     */
    fun getAllCartItems() {
        viewModelScope.launch {
            _cartItems.emit((Resource.loading(true)))
            checkoutAllItemsUC.getAllCheckoutItems().collect {
                _cartItems.emit(it)
            }
        }
    }

    /**
     * Delete cart item
     * This function deletes item from cart which in db
     * @param id
     */
    fun deleteCartItem(id: String) {
        viewModelScope.launch {
            deleteSneakersFromCartUseCase.deleteSneakerFromCart(id).collect {
                _deleteItem.emit(it)
            }
        }
    }
}