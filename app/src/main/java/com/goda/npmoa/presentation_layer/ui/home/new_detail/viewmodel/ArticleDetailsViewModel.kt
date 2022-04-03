package com.goda.npmoa.presentation_layer.ui.home.new_detail.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.goda.npmoa.data_layer.AppDataManager
import com.goda.npmoa.data_layer.model.Result
import com.goda.npmoa.data_layer.model.db.Article
import com.goda.npmoa.presentation_layer.ui.base.BaseViewModel
import com.goda.npmoa.presentation_layer.ui.home.new_detail.ArticleDetailsNavigator
import com.goda.npmoa.presentation_layer.ui.home.news.datamodel.ArticleDataItem
import kotlinx.coroutines.launch

class ArticleDetailsViewModel(
    application: Application,
    appDataManager: AppDataManager
) : BaseViewModel<ArticleDetailsNavigator>(application, appDataManager) {
    private val isFavorite: MutableLiveData<Boolean> = MutableLiveData()

    private fun insertArticle(articleDataItem: ArticleDataItem) {
        launch {
            appDataManager.getDbRepository().insertArticle(
                Article(
                    articleDataItem.id
                    , articleDataItem.imageUrl
                    , articleDataItem.title
                    ,  articleDataItem.byline
                    ,  articleDataItem.section
                    , articleDataItem.abstractX
                    , articleDataItem.publishedDate
                    , articleDataItem.url
                    , articleDataItem.coverImageUrl
                )
            )
            isFavorite.value = true
        }
    }

    private fun deleteArticle(articleDataItem: ArticleDataItem) {
        launch {
            appDataManager.getDbRepository().deleteArticle(
                Article(
                    articleDataItem.id
                    , articleDataItem.imageUrl
                    , articleDataItem.title
                /*    , section = articleDataItem.section
                    , source = articleDataItem.source*/
                    ,  articleDataItem.byline
                    ,  articleDataItem.section

                    , articleDataItem.abstractX
                    , articleDataItem.publishedDate
                    , articleDataItem.url
                    , articleDataItem.coverImageUrl
                )
            )
            isFavorite.value = false
        }
    }

    fun findById(id: Long) {
        launch {
            when (appDataManager.getDbRepository().findById(id)) {
                is Result.Success<Article> -> {
                    isFavorite.value = true
                }
                is Result.Error -> {
                    isFavorite.value = false
                }
            }
        }
    }

    fun onFavClick(
        isFavorite: Boolean,
        articleDataItem: ArticleDataItem
    ) {
        if (isFavorite) deleteArticle(articleDataItem) else insertArticle(articleDataItem)
    }

    fun getIsFavorite(): LiveData<Boolean> {
        return isFavorite
    }

}