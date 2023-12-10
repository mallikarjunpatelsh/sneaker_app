package com.cricbuzz.sneakersapp.ui.model

import com.cricbuzz.sneakersapp.domain.entities.ProductEntity

/**
 * Checkout model
 * UI model for checkout screen
 */
data class CheckoutModel(
    var itemList: List<ProductEntity>?,
    var sub_total: Int = 0,
    var taxes: Int = 40,
    var total_price: Int = 0
)
