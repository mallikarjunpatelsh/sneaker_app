package com.cricbuzz.sneakersapp.ui.model


/**
 * SneakerUIModel model
 * UI model for Sneaker details page
 */
data class SneakerUIModel(
    var id: String?,
    var imageUrl: String?,
    var name: String?,
    var retailPrice: Int=0,
    var title: String?,
    var sizes: List<SizeModel>?
) {
}