package com.cricbuzz.sneakersapp.data.repository

import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {
    fun fetchSneakers(): Flow<Resource<List<SneakerDetail>?>>
    fun fetchSneakerDetail(id: String): Flow<Resource<SneakerDetail?>>
    fun addSneakerToCart(productEntity: ProductEntity): Flow<Resource<Long>>
}