package com.cricbuzz.sneakersapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cricbuzz.sneakersapp.databinding.LayoutImageItemBinding
import com.cricbuzz.sneakersapp.ui.viewholder.ProductImageViewHolder

/**
 * Product image adapter
 * binds the product image in details screen
 * @constructor Create empty Product image adapter
 */
class ProductImageAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var imageUrl: String? = null
    /**
     * On create view holder
     *
     * @param parent
     * @param viewType
     * @return ProductImageViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductImageViewHolder(LayoutImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    /**
     * Get item count
     *
     * @return
     */
    override fun getItemCount(): Int {
        return 4
    }

    /**
     * On bind view holder
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        imageUrl?.let {
            (holder as ProductImageViewHolder).bind(it)
        }
    }

    /**
     * Set images
     * set image url
     * notify UI
     * @param imageUrl
     */
    fun setImages(imageUrl: String?) {
        this.imageUrl = imageUrl
        notifyDataSetChanged()
    }
}