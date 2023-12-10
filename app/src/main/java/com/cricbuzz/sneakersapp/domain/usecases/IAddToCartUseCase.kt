package com.cricbuzz.sneakersapp.domain.usecases

import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.ui.model.SneakerUIModel
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow

interface IAddToCartUseCase {
    fun addSneakerToCart(productEntity: SneakerUIModel): Flow<Resource<Long>>
}