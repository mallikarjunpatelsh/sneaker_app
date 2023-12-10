package com.cricbuzz.sneakersapp.di.module

import com.cricbuzz.sneakersapp.data.datasource.ProductLocalDatasourceImpl
import com.cricbuzz.sneakersapp.data.datasource.SneakersRemoteDatasourceImpl
import com.cricbuzz.sneakersapp.data.datasource.IProductLocalDatasource
import com.cricbuzz.sneakersapp.data.datasource.ISneakersRemoteDatasource
import com.cricbuzz.sneakersapp.di.qualifier.LocalDataSource
import com.cricbuzz.sneakersapp.di.qualifier.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  This class is used to provides all the bind-able Data sources (Remote & Local)
 */
@InstallIn(SingletonComponent::class)
@Module
interface DataSourceModule {

    /**
     * This Remote data source will be for entire home module
     */
    @Binds
    @Singleton
    @RemoteDataSource
    fun bindUserRemoteDataSource(homeDatasourceImpl: SneakersRemoteDatasourceImpl): ISneakersRemoteDatasource

    /**
     * This Local data source will be for entire checkout module
     */
    @Binds
    @Singleton
    @LocalDataSource
    fun bindCheckoutDataSource(checkoutDatasourceImpl: ProductLocalDatasourceImpl): IProductLocalDatasource
}