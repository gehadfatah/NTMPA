package com.goda.npmoa.presentation_layer.ui.home.news.di

import com.goda.npmoa.presentation_layer.ui.home.news.fragment.ArticleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ArticleFragmentProvider {
    @ContributesAndroidInjector(modules = [ArticleFragmentModule::class])
    abstract fun provideArticleFragmentFactory(): ArticleFragment
}