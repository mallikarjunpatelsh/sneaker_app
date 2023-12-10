package com.cricbuzz.sneakersapp.data.datasource

import android.content.Context
import com.cricbuzz.sneakersapp.data.dao.ICheckoutDao
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.utils.network.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Checkout datasource impl
 * extends BaseDatasource
 * @property context
 * @property checkoutDao - instance of ICheckoutDao added via hilt
 * @constructor Create empty Checkout datasource impl
 */
class ProductLocalDatasourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val checkoutDao: ICheckoutDao
) : IProductLocalDatasource, BaseDataSource(context) {
    /**
     * Add sneaker to cart
     * adds the sneaker to the product table in db
     * @param productEntity - entity to be inserted in db
     * @return flow of inserted rowId
     */
    override fun addProductToCart(productEntity: ProductEntity): Flow<Resource<Long>> = getResult{
        checkoutDao.addToCart(productEntity)
    }

    /**
     * Get all checkout items
     * THis function will the emit the result from the BaseDatasource
     * Since BaseDatasource is parent and using getResult function, given operation is done and value is emitted
     * @return Flow of Resource of Products List
     */
    override fun getAllCheckoutItems(): Flow<Resource<List<ProductEntity>>> = getResult{
        delay(500)
        checkoutDao.getAllCartItems()
    }

    /**
     * Delete product to cart
     *
     * @param productEntity
     * @return
     */
    override fun deleteProductFromCart(id: String): Flow<Resource<Int?>> = getResult{
        checkoutDao.deleteCartItem(id)
    }
}