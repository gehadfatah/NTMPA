package com.goda.npmoa.presentation_layer.ui.home.news.di

import com.goda.npmoa.presentation_layer.ui.home.news.adapter.ArticleAdapter
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class ArticleFragmentModule {
    @Provides
    fun provideArticleAdapter(): ArticleAdapter {
        return ArticleAdapter(ArrayList())
    }
}