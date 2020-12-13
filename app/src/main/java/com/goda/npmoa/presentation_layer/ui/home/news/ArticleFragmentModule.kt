package com.goda.npmoa.presentation_layer.ui.home.news

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