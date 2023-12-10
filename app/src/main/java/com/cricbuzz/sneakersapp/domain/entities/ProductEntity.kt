package com.cricbuzz.sneakersapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cricbuzz.sneakersapp.utils.AppConstants.DBConstants.Companion.PRODUCT_ENTITY

/**
 * Product entity
 * Table product in db
 * @property id
 * @property imageUrl
 * @property price
 * @property name
 * @property quantity
 * @constructor Create empty Product entity
 */
@Entity(tableName = PRODUCT_ENTITY)
data class ProductEntity(
    @PrimaryKey var id: String = "0",
    var imageUrl: String? = null,
    var price: Int = 0,
    var name: String? = null,
    var quantity: Int = 0
    )
