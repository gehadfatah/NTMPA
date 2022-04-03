package com.goda.npmoa.di.builder

import com.goda.npmoa.presentation_layer.ui.home.HomeActivity
import com.goda.npmoa.presentation_layer.ui.home.di.HomeActivityModule
import com.goda.npmoa.presentation_layer.ui.home.news.di.ArticleFragmentProvider
import com.goda.npmoa.presentation_layer.ui.home.new_detail.di.ArticleDetailsFragmentProvider
import com.goda.npmoa.presentation_layer.ui.home.favorites.di.FavoritesFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [HomeActivityModule::class, ArticleFragmentProvider::class, ArticleDetailsFragmentProvider::class, FavoritesFragmentProvider::class])
    abstract fun bindHomeActivity(): HomeActivity
}