package com.cricbuzz.sneakersapp.data.repository

import com.cricbuzz.sneakersapp.data.datasource.IProductLocalDatasource
import com.cricbuzz.sneakersapp.data.datasource.ISneakersRemoteDatasource
import com.cricbuzz.sneakersapp.di.qualifier.LocalDataSource
import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.utils.network.Resource
import com.cricbuzz.sneakersapp.di.qualifier.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Home repository impl
 *
 * @property remoteDatasource
 * @property localDatasource
 * @constructor Create empty Home repository impl
 */
class HomeRepositoryImpl @Inject constructor(
    @RemoteDataSource private val remoteDatasource: ISneakersRemoteDatasource,
    @LocalDataSource private val localDatasource: IProductLocalDatasource
    ): IHomeRepository {
    /**
     * Fetch sneakers
     * call the remote datasource for sneakers
     * wait for the response from datasource and emit this bsck to usecase
     * @return flow of products list
     */
    override fun fetchSneakers(): Flow<Resource<List<SneakerDetail>?>> = flow{
        remoteDatasource.fetchSneakers().collect {
            emit(it)
        }
    }

    /**
     * Fetch sneaker detail
     * call the remote datasource for sneaker detail for given id
     * wait for the response from datasource and emit this bsck to usecase
     * @param id
     * @return flow of sneaker detail
     */
    override fun fetchSneakerDetail(id: String): Flow<Resource<SneakerDetail?>> = flow{
        remoteDatasource.fetchSneakerDetail(id).collect {
            emit(it)
        }
    }

    /**
     * Add sneaker to cart
     * call the local datasource to add the sneaker into db
     * @param productEntity
     * @return flow of inserted rowId
     */
    override fun addSneakerToCart(productEntity: ProductEntity): Flow<Resource<Long>> = flow{
        localDatasource.addProductToCart(productEntity).collect {
            emit(it)
        }
    }
}