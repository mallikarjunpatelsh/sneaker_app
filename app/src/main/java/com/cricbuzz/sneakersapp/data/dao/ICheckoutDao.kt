package com.cricbuzz.sneakersapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.utils.AppConstants

/**
 * Dao for checkout
 * this interface contains all the db queries required for cart
 */
@Dao
interface ICheckoutDao {

    /**
     * Add to cart
     * This function adds the product tto checkout table in db
     * onConflict = OnConflictStrategy.REPLACE replace if the data is already present in db
     * @param productEntity - entity class to be added to table
     * @return the rowId for inserted data
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(productEntity: ProductEntity): Long

    /**
     * Get all cart items
     * Fetches all the product present under checkout table
     * @return list of product entities
     */
    @Query("SELECT * FROM ${AppConstants.DBConstants.PRODUCT_ENTITY}")
    suspend fun getAllCartItems(): List<ProductEntity>

    /**
     * Delete cart item
     * @param id - id of the record to be deleted
     * @return - no of rows deleted
     */
    @Query("DELETE FROM ${AppConstants.DBConstants.PRODUCT_ENTITY}" +
            " WHERE id = :id"
    )
    suspend fun deleteCartItem(id: String): Int?
}