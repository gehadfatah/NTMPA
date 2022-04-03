package com.goda.npmoa.presentation_layer.ui.home.favorites.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.goda.npmoa.data_layer.model.db.Article
import com.goda.npmoa.databinding.EmptyFavoritesBinding
import com.goda.npmoa.databinding.ListItemFavoriteBinding
import com.goda.npmoa.presentation_layer.ui.base.listeners.BaseItemListener
import com.goda.npmoa.presentation_layer.ui.base.adapters.BaseRecyclerViewAdapter
import com.goda.npmoa.presentation_layer.ui.base.BaseViewHolder
import com.goda.npmoa.presentation_layer.ui.home.favorites.models.FavoritesItemViewModel
import com.goda.npmoa.presentation_layer.ui.home.favorites.models.FavoritesItemViewModel.FavoritesItemViewModelListener
import com.goda.npmoa.utils.NYPConstants.VIEW_TYPE_EMPTY
import com.goda.npmoa.utils.NYPConstants.VIEW_TYPE_NORMAL

class FavoritesAdapter(items: MutableList<Article>) :
    BaseRecyclerViewAdapter<Article>(items) {
    private lateinit var mListener: FavoritesAdapterListener

    fun setListener(listener: FavoritesAdapterListener) {
        mListener = listener
    }

    override fun getItemCount(): Int {
        return if (items.size > 0) items.size else 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isNotEmpty()) VIEW_TYPE_NORMAL else VIEW_TYPE_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                FavoritesViewHolder(
                    ListItemFavoriteBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            else -> {
                EmptyViewHolder(
                    EmptyFavoritesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }

    interface FavoritesAdapterListener :
        BaseItemListener<Article>

    inner class FavoritesViewHolder(private val mBinding: ListItemFavoriteBinding) :
        BaseViewHolder(mBinding.root), FavoritesItemViewModelListener {
        override fun onBind(position: Int) {
            val article = items[position]
            mBinding.viewModel =
                FavoritesItemViewModel(
                    article,
                    this
                )
            mBinding.executePendingBindings()
        }

        override fun onItemClick(item: Article) {
            mListener.onItemClick(item)
        }

    }

    inner class EmptyViewHolder(private val mBinding: EmptyFavoritesBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {

            mBinding.executePendingBindings()
        }
    }

}