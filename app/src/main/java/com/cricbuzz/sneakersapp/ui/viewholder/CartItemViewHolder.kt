package com.cricbuzz.sneakersapp.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cricbuzz.sneakersapp.R
import com.cricbuzz.sneakersapp.databinding.LayoutCheckoutItemBinding
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.ui.callbacks.ICheckoutClickCallback
import com.cricbuzz.sneakersapp.ui.view.CheckoutFragment

/**
 * Cart item view holder
 *
 * @property binding
 * @constructor Create empty Cart item view holder
 */
class CartItemViewHolder(
    private val binding: LayoutCheckoutItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    /**
     * Bind
     * this function binds the data to ui
     * @param productEntity
     * @param callback
     */
    fun bind(productEntity: ProductEntity, callback: ICheckoutClickCallback) {
        Glide.with(binding.ivProduct)
            .load(productEntity.imageUrl)
            .into(binding.ivProduct)
            .apply { RequestOptions()
                .error(R.drawable.baseline_sync_24)
                .placeholder(R.drawable.baseline_sync_24) }

        binding.product = productEntity
        binding.deleteButton.setOnClickListener { callback.onClick(productEntity.id) }
    }

}