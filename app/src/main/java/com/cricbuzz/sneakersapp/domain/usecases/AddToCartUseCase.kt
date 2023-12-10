package com.cricbuzz.sneakersapp.domain.usecases

import com.cricbuzz.sneakersapp.data.repository.IHomeRepository
import com.cricbuzz.sneakersapp.di.qualifier.Repository
import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.ui.model.SneakerUIModel
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Add to cart use case
 *
 * @property repository
 * @constructor Create empty Add to cart use case
 */
class AddToCartUseCase @Inject constructor(@Repository private val repository: IHomeRepository): IAddToCartUseCase {

    /**
     * Add sneaker to cart
     * calls repo to add sneaker to db and emit the response
     * @param productEntity - sneaker details which will be converted to entity
     * @return flow of inserted id
     */
    override fun addSneakerToCart(productEntity: SneakerUIModel): Flow<Resource<Long>> = flow{
        repository.addSneakerToCart(convertDataToEntity(productEntity)).collect {
            emit(it)
        }
    }

    /**
     * Convert data to entity
     * This converts the SneakerDetail to ProductEntity
     * @param sneakerDetail
     * @return product entity
     */
    private fun convertDataToEntity(sneakerDetail: SneakerUIModel) : ProductEntity {
        return ProductEntity(
            id = sneakerDetail.id?:"",
            name = sneakerDetail.name?:"",
            price = sneakerDetail.retailPrice,
            quantity = 1,
            imageUrl = sneakerDetail.imageUrl
        )
    }

}