package com.cricbuzz.sneakersapp.data.repository

import com.cricbuzz.sneakersapp.data.datasource.IProductLocalDatasource
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.utils.network.Resource
import com.cricbuzz.sneakersapp.di.qualifier.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Checkout repository
 *
 * @property datasource
 * @constructor Create empty Checkout repository
 */
class CheckoutRepository @Inject constructor(
    @LocalDataSource private val datasource: IProductLocalDatasource
) : ICheckoutRepository{
    /**
     * Get all cart items
     * calls the datasource to get all items in cart
     * waits for response and emit back
     * @return flow products list
     */
    override fun getAllCartItems(): Flow<Resource<List<ProductEntity>>> = flow{
        datasource.getAllCheckoutItems().collect {
            emit(it)
        }
    }

    override fun deleteSneakerFromCart(id: String): Flow<Resource<Int?>> = flow{
        datasource.deleteProductFromCart(id).collect {
            emit(it)
        }
    }
}