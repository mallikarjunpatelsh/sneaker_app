package com.cricbuzz.sneakersapp.data.datasource

import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow

interface IProductLocalDatasource {
    fun getAllCheckoutItems(): Flow<Resource<List<ProductEntity>>>
    fun addProductToCart(productEntity: ProductEntity): Flow<Resource<Long>>
    fun deleteProductFromCart(id: String): Flow<Resource<Int?>>
}