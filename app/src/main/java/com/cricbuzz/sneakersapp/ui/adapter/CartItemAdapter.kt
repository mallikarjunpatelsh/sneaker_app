package com.cricbuzz.sneakersapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cricbuzz.sneakersapp.databinding.IncludeEmptyViewholderLayoutBinding
import com.cricbuzz.sneakersapp.databinding.LayoutCheckoutItemBinding
import com.cricbuzz.sneakersapp.databinding.LayoutOrderPriceDetailsBinding
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.ui.callbacks.ICheckoutClickCallback
import com.cricbuzz.sneakersapp.ui.model.CheckoutModel
import com.cricbuzz.sneakersapp.ui.viewholder.CartItemViewHolder
import com.cricbuzz.sneakersapp.ui.viewholder.EmptyViewHolder
import com.cricbuzz.sneakersapp.ui.viewholder.OrderPriceViewHolder

/**
 * Cart item adapter
 * Binds the cart items
 * @constructor Create empty Cart item adapter
 */
class CartItemAdapter(private val callback: ICheckoutClickCallback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var checkoutModel: CheckoutModel? = null
    private var cartItems: List<ProductEntity>? = null

    /**
     * On create view holder
     *
     * @param parent
     * @param viewType
     * @return CartItemViewHolder instance
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE -> CartItemViewHolder(
                LayoutCheckoutItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )

            ORDER_PRICE_DETAIL -> OrderPriceViewHolder(
                LayoutOrderPriceDetailsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> EmptyViewHolder(
                IncludeEmptyViewholderLayoutBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    /**
     * Get item count
     *
     * @return countg of cart items
     */
    override fun getItemCount(): Int {
        return cartItems?.size?.plus(1) ?: 0
    }

    /**
     * Get item view type
     * Checking for view type
     * @param position
     * @return
     */
    override fun getItemViewType(position: Int): Int {
        return if (cartItems?.size == 0) INVALID_TYPE
        else if ((cartItems?.size ?: 0) > position) ITEM_VIEW_TYPE
        else ORDER_PRICE_DETAIL
    }

    /**
     * On bind view holder
     * calls the bind function in CartItemViewHolder which handles the view
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_VIEW_TYPE -> {
                cartItems?.get(position)?.let {
                    (holder as? CartItemViewHolder)?.bind(it, callback)
                }
            }
            ORDER_PRICE_DETAIL -> {
                (holder as? OrderPriceViewHolder)?.bind(checkoutModel)
            }
            else -> {

            }
        }
    }

    /**
     * Set list
     * assign the list to cartItems
     * notify data set change to update the view
     * @param list
     */
    fun setList(list: CheckoutModel?) {
        checkoutModel = list
        cartItems = list?.itemList
        notifyDataSetChanged()
    }


    /**
     * Companion object
     * declared properties will be static available
     */
    companion object {
        const val INVALID_TYPE = 0
        const val ITEM_VIEW_TYPE = 1
        const val ORDER_PRICE_DETAIL = 2
    }

}