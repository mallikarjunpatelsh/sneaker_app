package com.cricbuzz.sneakersapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cricbuzz.sneakersapp.databinding.LayoutProductListItemBinding
import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.ui.callbacks.IProductListClickCallback
import com.cricbuzz.sneakersapp.ui.viewholder.ProductViewHolder

/**
 * Products list adapter
 * binds the products to view
 * @constructor Create empty Products list adapter
 */
class ProductsListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var productsList: List<SneakerDetail>? = null
    private var callback: IProductListClickCallback? = null

    /**
     * On create view holder
     *
     * @param parent
     * @param viewType
     * @return ProductViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(LayoutProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    /**
     * Get item count
     *
     * @return productlist size
     */
    override fun getItemCount(): Int {
        return productsList?.size?:0
    }

    /**
     * On bind view holder
     * ProductViewHolder binds the view
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        productsList?.get(position)?.let {
            (holder as ProductViewHolder).bind(it, callback)
        }
    }

    /**
     * Set products list
     * sets the data and notify to update view
     * @param data
     */
    fun setProductsList(data: List<SneakerDetail>?) {
        productsList = data
        notifyDataSetChanged()
    }

    /**
     * Set callback
     * @param onClickCallback
     */
    fun setCallback(onClickCallback: IProductListClickCallback) {
        callback = onClickCallback
    }
}