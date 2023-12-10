package com.cricbuzz.sneakersapp.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.cricbuzz.sneakersapp.R
import com.cricbuzz.sneakersapp.databinding.LayoutOrderPriceDetailsBinding
import com.cricbuzz.sneakersapp.ui.model.CheckoutModel

class OrderPriceViewHolder(
    private val binding: LayoutOrderPriceDetailsBinding
) : RecyclerView.ViewHolder(binding.root) {
    /**
     * Bind
     * this function binds the data to ui
     * @param checkoutModel
     */
    fun bind(checkoutModel: CheckoutModel?) {
        binding.tvSubTotal.apply {
            text = context.getString(R.string.sub_total_price, checkoutModel?.sub_total?.toString())
        }
        binding.tvTotal.apply {
            text = context.getString(R.string.total_price, checkoutModel?.total_price?.toString())
        }
    }
}