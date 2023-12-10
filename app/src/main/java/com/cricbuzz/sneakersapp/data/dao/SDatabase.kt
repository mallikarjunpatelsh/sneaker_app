package com.cricbuzz.sneakersapp.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity

/**
 * S database
 * This is abstract class where the entities and version are specified in the database annotation
 * @constructor Create empty S database
 */
@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class SDatabase: RoomDatabase() {
    /**
     * Get checkout dao
     * @return ICheckoutDao instance
     */
    abstract fun getCheckoutDao(): ICheckoutDao
}