package com.cricbuzz.sneakersapp.domain.usecases

import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow

interface IDeleteSneakerFromCartUseCase {
    fun deleteSneakerFromCart(id: String): Flow<Resource<Int?>>
}