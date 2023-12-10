package com.cricbuzz.sneakersapp.domain.usecases

import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow

interface IProductListUseCase {
    fun fetchSneakers(): Flow<Resource<List<SneakerDetail>?>>
}