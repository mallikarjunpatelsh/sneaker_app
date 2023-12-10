package com.cricbuzz.sneakersapp.domain.usecases

import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.ui.model.CheckoutModel
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow

interface ICheckoutGetItemsUseCase {
    fun getAllCheckoutItems(): Flow<Resource<CheckoutModel?>>
}