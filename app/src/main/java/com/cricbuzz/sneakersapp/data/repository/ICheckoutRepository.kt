package com.cricbuzz.sneakersapp.data.repository

import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow

interface ICheckoutRepository {
    fun getAllCartItems(): Flow<Resource<List<ProductEntity>>>
    fun deleteSneakerFromCart(id: String): Flow<Resource<Int?>>
}