package com.goda.npmoa.presentation_layer.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.goda.npmoa.data_layer.AppDataManager
import com.goda.npmoa.presentation_layer.ui.home.HomeViewModel
import com.goda.npmoa.presentation_layer.ui.home.news.ArticleViewModel
import com.goda.npmoa.presentation_layer.ui.home.new_detail.ArticleDetailsViewModel
import com.goda.npmoa.presentation_layer.ui.home.favorites.FavoritesViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val application: Application,
    private val appDataManager: AppDataManager
) : NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(
                    application,
                    appDataManager
                ) as T
            }
            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                ArticleViewModel(
                    application,
                    appDataManager
                ) as T
            }
            modelClass.isAssignableFrom(ArticleDetailsViewModel::class.java) -> {
                ArticleDetailsViewModel(
                    application,
                    appDataManager
                ) as T
            }
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(
                    application,
                    appDataManager
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}