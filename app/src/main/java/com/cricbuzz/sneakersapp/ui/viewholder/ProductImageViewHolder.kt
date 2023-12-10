package com.cricbuzz.sneakersapp.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cricbuzz.sneakersapp.R
import com.cricbuzz.sneakersapp.databinding.LayoutImageItemBinding

class ProductImageViewHolder(
    private val binding: LayoutImageItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    /**
     * Bind
     * this function binds the data to ui
     * @param imageUrl
     */
    fun bind(imageUrl: String) {
        Glide.with(binding.root.context)
            .load(imageUrl)
            .into(binding.ivProduct)
            .apply { RequestOptions()
                .error(R.drawable.baseline_sync_24)
                .placeholder(R.drawable.baseline_sync_24) }
    }
}