package com.cricbuzz.sneakersapp.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cricbuzz.sneakersapp.R
import com.cricbuzz.sneakersapp.databinding.LayoutProductListItemBinding
import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.ui.callbacks.IProductListClickCallback

class ProductViewHolder(
        private val binding: LayoutProductListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
    /**
     * Bind
     * this function binds the data to ui
     * @param sneakerDetail
     */
    fun bind(sneakerDetail: SneakerDetail, callback: IProductListClickCallback?) {
        Glide.with(binding.root.context)
            .load(sneakerDetail.media?.imageUrl)
            .into(binding.productImageView)
            .apply { RequestOptions()
                .error(R.drawable.baseline_sync_24)
                .placeholder(R.drawable.baseline_sync_24) }

        binding.sneakerDetails = sneakerDetail
        binding.root.setOnClickListener {
            callback?.productOnClick(sneakerDetail.id ?:"")
        }
    }
}