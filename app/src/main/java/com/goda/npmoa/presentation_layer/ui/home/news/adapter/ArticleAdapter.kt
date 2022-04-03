package com.goda.npmoa.presentation_layer.ui.home.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.goda.npmoa.databinding.EmptyArticlesBinding
import com.goda.npmoa.databinding.ListItemArticleBinding
import com.goda.npmoa.presentation_layer.ui.base.listeners.BaseEmptyItemListener
import com.goda.npmoa.presentation_layer.ui.base.listeners.BaseItemListener
import com.goda.npmoa.presentation_layer.ui.base.adapters.BaseRecyclerViewAdapter
import com.goda.npmoa.presentation_layer.ui.base.BaseViewHolder
import com.goda.npmoa.presentation_layer.ui.home.news.datamodel.ArticleDataItem
import com.goda.npmoa.presentation_layer.ui.home.news.datamodel.ArticleEmptyItemViewModel
import com.goda.npmoa.presentation_layer.ui.home.news.viewmodel.ArticleItemViewModel
import com.goda.npmoa.presentation_layer.ui.home.news.viewmodel.ArticleItemViewModel.ArticleItemViewModelListener
import com.goda.npmoa.utils.NYPConstants.VIEW_TYPE_EMPTY
import com.goda.npmoa.utils.NYPConstants.VIEW_TYPE_NORMAL

class ArticleAdapter(items: MutableList<ArticleDataItem>) :
    BaseRecyclerViewAdapter<ArticleDataItem>(items) {
    private lateinit var mListener: ArticleAdapterListener

    fun setListener(listener: ArticleAdapterListener) {
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
                ArticleViewHolder(
                    ListItemArticleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            else -> {
                EmptyViewHolder(
                    EmptyArticlesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }

    interface ArticleAdapterListener : BaseItemListener<ArticleDataItem>,
        BaseEmptyItemListener

    inner class ArticleViewHolder(private val mBinding: ListItemArticleBinding) :
        BaseViewHolder(mBinding.root), ArticleItemViewModelListener {
        override fun onBind(position: Int) {
            val article = items[position]
            mBinding.viewModel =
                ArticleItemViewModel(
                    article,
                    this
                )
            mBinding.executePendingBindings()
        }

        override fun onItemClick(item: ArticleDataItem) {
            mListener.onItemClick(item)
        }

    }

    inner class EmptyViewHolder(private val mBinding: EmptyArticlesBinding) :
        BaseViewHolder(mBinding.root),
        BaseEmptyItemListener {
        override fun onBind(position: Int) {
            mBinding.viewModel =
                ArticleEmptyItemViewModel(
                    this
                )
            mBinding.executePendingBindings()
        }

        override fun onRetryClick() {
            mListener.onRetryClick()
        }

    }

}