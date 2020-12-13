package com.goda.npmoa.presentation_layer.ui.home.favorites

import androidx.databinding.ObservableField
import com.goda.npmoa.data_layer.model.db.Article
import com.goda.npmoa.presentation_layer.ui.base.BaseItemListener

class FavoritesItemViewModel(
    private val article: Article,
    private val mListener: FavoritesItemViewModelListener
) {
    val imageUrl: ObservableField<String?> = ObservableField(article.imageUrl)
    val title: ObservableField<String?> = ObservableField(article.title)
    val byline: ObservableField<String?> = ObservableField(article.byline)
    val publishedDate: ObservableField<String?> = ObservableField(article.publishedDate)

    fun onItemClick() {
        mListener.onItemClick(article)
    }

    interface FavoritesItemViewModelListener :
        BaseItemListener<Article>

}