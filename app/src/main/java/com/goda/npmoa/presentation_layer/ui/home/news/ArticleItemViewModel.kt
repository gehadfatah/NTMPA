package com.goda.npmoa.presentation_layer.ui.home.news

import androidx.databinding.ObservableField
import com.goda.npmoa.presentation_layer.ui.base.BaseItemListener

class ArticleItemViewModel(
    private val article: ArticleDataItem,
    private val mListener: ArticleItemViewModelListener
) {
    val imageUrl: ObservableField<String?> = ObservableField(article.imageUrl)
    val title: ObservableField<String?> = ObservableField(article.title)
    val byline: ObservableField<String?> = ObservableField(article.byline)
    val publishedDate: ObservableField<String?> = ObservableField(article.publishedDate)

    fun onItemClick() {
        mListener.onItemClick(article)
    }

    interface ArticleItemViewModelListener :
        BaseItemListener<ArticleDataItem>

}