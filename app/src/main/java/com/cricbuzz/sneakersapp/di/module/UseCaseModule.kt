package com.cricbuzz.sneakersapp.di.module

import com.cricbuzz.sneakersapp.domain.usecases.AddToCartUseCase
import com.cricbuzz.sneakersapp.domain.usecases.CheckoutGetItemsUseCaseImpl
import com.cricbuzz.sneakersapp.domain.usecases.DeleteSneakersFromCartUseCase
import com.cricbuzz.sneakersapp.domain.usecases.IAddToCartUseCase
import com.cricbuzz.sneakersapp.domain.usecases.ICheckoutGetItemsUseCase
import com.cricbuzz.sneakersapp.domain.usecases.IDeleteSneakerFromCartUseCase
import com.cricbuzz.sneakersapp.domain.usecases.IProductDetailUseCase
import com.cricbuzz.sneakersapp.domain.usecases.IProductListUseCase
import com.cricbuzz.sneakersapp.domain.usecases.ProductDetailUseCase
import com.cricbuzz.sneakersapp.domain.usecases.ProductsListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  This class is used to provides all the use case
 */
@InstallIn(SingletonComponent::class)
@Module
interface UseCaseModule {
    /**
     * This binds the product list use case
     */
    @Binds
    @Singleton
    fun bindProductListUseCase(productsListUseCase: ProductsListUseCaseImpl): IProductListUseCase

    /**
     * This binds the product detail use case
     */
    @Binds
    @Singleton
    fun bindProductDetailUseCase(productsListUseCase: ProductDetailUseCase): IProductDetailUseCase

    /**
     * This binds the add to cart use case
     */
    @Binds
    @Singleton
    fun bindAddToCartUseCase(addToCartUseCase: AddToCartUseCase): IAddToCartUseCase

    /**
     * This binds the get cart use case
     */
    @Binds
    @Singleton
    fun bindGetCartItemsUseCase(checkoutGetItemsUseCaseImpl: CheckoutGetItemsUseCaseImpl): ICheckoutGetItemsUseCase

    /**
     * This binds the delete cart item use case
     */
    @Binds
    @Singleton
    fun binddeleteCartItemsUseCase(deleteSneakersFromCartUseCase: DeleteSneakersFromCartUseCase): IDeleteSneakerFromCartUseCase
}