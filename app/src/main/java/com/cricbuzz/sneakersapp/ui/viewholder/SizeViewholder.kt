package com.cricbuzz.sneakersapp.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.cricbuzz.sneakersapp.databinding.LayoutSizeItemBinding
import com.cricbuzz.sneakersapp.ui.callbacks.ISizeSelectionCallabck
import com.cricbuzz.sneakersapp.ui.model.SizeModel

class SizeViewholder(
        private val binding: LayoutSizeItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
    /**
     * Bind
     * this function binds the data to ui
     * @param s
     * @param callback
     */
    fun bind(s: SizeModel, callback: ISizeSelectionCallabck) {
        binding.tvSize.text = s.size
        binding.tvSize.isSelected = s.isSelected
        binding.tvSize.setOnClickListener { s.size?.let { it1 -> callback.selected(it1) } }
    }

}