package com.goda.npmoa.presentation_layer.ui.home.favorites

import android.app.Application
import androidx.lifecycle.LiveData
import com.goda.npmoa.data_layer.AppDataManager
import com.goda.npmoa.data_layer.model.db.Article
import com.goda.npmoa.presentation_layer.ui.base.BaseViewModel

class FavoritesViewModel(
    application: Application,
    appDataManager: AppDataManager
) : BaseViewModel<FavoritesNavigator>(application, appDataManager) {
    val articlesLiveDataLiveData: LiveData<List<Article>> = appDataManager.getDbRepository().allArticles()
}