package com.goda.npmoa.presentation_layer.ui.home.news

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ArticleFragmentProvider {
    @ContributesAndroidInjector(modules = [ArticleFragmentModule::class])
    abstract fun provideArticleFragmentFactory(): ArticleFragment
}