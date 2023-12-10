package com.cricbuzz.sneakersapp.domain.data

/**
 * Sneaker detail
 * Data class for 
 * @property brand
 * @property colorway
 * @property gender
 * @property id
 * @property media
 * @property name
 * @property releaseDate
 * @property retailPrice
 * @property shoe
 * @property styleId
 * @property title
 * @property year
 * @constructor Create empty Sneaker detail
 */
data class SneakerDetail(
    var brand: String?,
    var colorway: String?,
    var gender: String?,
    var id: String?,
    var media: Media?,
    var name: String?,
    var releaseDate: String?,
    var retailPrice: Int=0,
    var shoe: String?,
    var styleId: String?,
    var title: String?,
    var year: Int = 0,
)