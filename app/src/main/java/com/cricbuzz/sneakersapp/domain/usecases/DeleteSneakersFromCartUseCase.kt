package com.cricbuzz.sneakersapp.domain.usecases

import com.cricbuzz.sneakersapp.data.repository.ICheckoutRepository
import com.cricbuzz.sneakersapp.di.qualifier.Repository
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteSneakersFromCartUseCase @Inject constructor(
    @Repository private val checkoutRepository: ICheckoutRepository
): IDeleteSneakerFromCartUseCase {
    override fun deleteSneakerFromCart(id: String): Flow<Resource<Int?>> = flow{
        checkoutRepository.deleteSneakerFromCart(id).collect {
            emit(it)
        }
    }

}