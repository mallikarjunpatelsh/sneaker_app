package com.cricbuzz.sneakersapp.di.module

import com.cricbuzz.sneakersapp.data.repository.CheckoutRepository
import com.cricbuzz.sneakersapp.data.repository.HomeRepositoryImpl
import com.cricbuzz.sneakersapp.data.repository.ICheckoutRepository
import com.cricbuzz.sneakersapp.data.repository.IHomeRepository
import com.cricbuzz.sneakersapp.di.qualifier.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  This class is used to provides all the repositories
 */
@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    /**
     * This Home repository will be for entire home module
     */
    @Singleton
    @Binds
    @Repository
    fun bindUserRepository(homeRepository: HomeRepositoryImpl): IHomeRepository

    /**
     * This Home repository will be for entire checkout module
     */
    @Singleton
    @Binds
    @Repository
    fun bindCheckoutRepository(checkoutRepository: CheckoutRepository): ICheckoutRepository
}