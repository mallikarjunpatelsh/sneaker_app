package com.cricbuzz.sneakersapp.domain.usecases

import com.cricbuzz.sneakersapp.data.repository.IHomeRepository
import com.cricbuzz.sneakersapp.di.qualifier.Repository
import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Products list use case impl
 *
 * @property repository
 * @constructor Create empty Products list use case impl
 */
class ProductsListUseCaseImpl @Inject constructor(@Repository private val repository: IHomeRepository): IProductListUseCase {
    /**
     * Fetch sneakers
     *
     * @return flow of sneakers list
     */
    override fun fetchSneakers(): Flow<Resource<List<SneakerDetail>?>> = flow{
        repository.fetchSneakers().collect {
            emit(it)
        }
    }
}