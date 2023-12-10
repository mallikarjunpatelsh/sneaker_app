package com.cricbuzz.sneakersapp.data.datasource

import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow

interface ISneakersRemoteDatasource {
    fun fetchSneakers(): Flow<Resource<List<SneakerDetail>?>>
    fun fetchSneakerDetail(id: String): Flow<Resource<SneakerDetail?>>
}