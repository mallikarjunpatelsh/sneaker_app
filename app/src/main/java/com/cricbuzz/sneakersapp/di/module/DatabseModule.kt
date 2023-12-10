package com.cricbuzz.sneakersapp.di.module

import android.content.Context
import androidx.room.Room
import com.cricbuzz.sneakersapp.data.dao.ICheckoutDao
import com.cricbuzz.sneakersapp.data.dao.SDatabase
import com.cricbuzz.sneakersapp.utils.AppConstants.DBConstants.Companion.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  This class is used to provides all DAO's
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabseModule {

    /**
     * Provide database
     * This function provides the Database instance
     * binds the database instaance
     * @param applicationContext
     * @return database instance
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context) :SDatabase {
        return Room.databaseBuilder(applicationContext, SDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Provide checkout dao
     *
     * @param sDatabase
     * @return ICheckoutDao
     */
    @Provides
    @Singleton
    fun provideCheckoutDao(sDatabase: SDatabase): ICheckoutDao {
        return sDatabase.getCheckoutDao()
    }

}