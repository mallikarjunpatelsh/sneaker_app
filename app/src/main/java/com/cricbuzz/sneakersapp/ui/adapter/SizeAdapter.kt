package com.cricbuzz.sneakersapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cricbuzz.sneakersapp.databinding.LayoutSizeItemBinding
import com.cricbuzz.sneakersapp.ui.callbacks.ISizeSelectionCallabck
import com.cricbuzz.sneakersapp.ui.model.SizeModel
import com.cricbuzz.sneakersapp.ui.viewholder.SizeViewholder

/**
 * Size adapter
 *
 * @constructor Create empty Size adapter
 */
class SizeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(), ISizeSelectionCallabck {
    private var list : List<SizeModel>? = null

    /**
     * On create view holder
     * creating Szie viewholder
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SizeViewholder(LayoutSizeItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    /**
     * Get item count
     * return item count
     * @return
     */
    override fun getItemCount(): Int {
        return list?.size?:0
    }

    /**
     * On bind view holder
     * binds the view
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        list?.get(position)?.let { (holder as? SizeViewholder)?.bind(it, this) }
    }

    /**
     * Selected
     * functions handles the size selction
     * @param s
     */
    override fun selected(s: String) {
        list?.forEach {
            if (it.size == s)
                it.isSelected = !it.isSelected
            else
                it.isSelected = false
        }
        notifyDataSetChanged()
    }

    /**
     * Set list
     * notify ui after setting sizes property
     * @param sizes
     */
    fun setList(sizes: List<SizeModel>?) {
        list = sizes
        notifyDataSetChanged()
    }

}