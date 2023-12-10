package com.cricbuzz.sneakersapp.domain.usecases

import com.cricbuzz.sneakersapp.data.repository.ICheckoutRepository
import com.cricbuzz.sneakersapp.di.qualifier.Repository
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.ui.model.CheckoutModel
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Checkout get items use case impl
 *
 * @property repository
 * @constructor Create empty Checkout get items use case impl
 */
class CheckoutGetItemsUseCaseImpl @Inject constructor(@Repository private val repository: ICheckoutRepository): ICheckoutGetItemsUseCase {
    /**
     * Get all checkout items
     * emits response given from repo
     * @return flow of products list
     */
    override fun getAllCheckoutItems(): Flow<Resource<CheckoutModel?>>  = flow{
        repository.getAllCartItems().collect {
            if (it.status == Resource.Status.SUCCESS)
                emit(Resource.success(convertEntitiesIntoUIModel(it.data), it.message?:"", it.code, false))
            else
                emit(Resource.error(null, it.message?:"", it.code, false))
        }
    }

    /**
     * Convert entities into ui model
     *
     * @param productEntity
     * @return
     */
    private fun convertEntitiesIntoUIModel(productEntity: List<ProductEntity>?): CheckoutModel? {
        if (productEntity == null) return null
        val model = CheckoutModel(
            itemList = productEntity
        )
        productEntity.forEach {
            model.sub_total = model.sub_total.plus(it.price)
        }
        model.total_price = model.sub_total + model.taxes
        return model
    }

}