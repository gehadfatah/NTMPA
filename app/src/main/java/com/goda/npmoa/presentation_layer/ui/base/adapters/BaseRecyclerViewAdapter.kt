package com.goda.npmoa.presentation_layer.ui.base.adapters

import androidx.recyclerview.widget.RecyclerView
import com.goda.npmoa.presentation_layer.ui.base.BaseViewHolder

abstract class BaseRecyclerViewAdapter<T>(val items: MutableList<T>) :
    RecyclerView.Adapter<BaseViewHolder>() {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItems(items: List<T>?) {
        items?.let { this.items.addAll(it) }
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
    }

}