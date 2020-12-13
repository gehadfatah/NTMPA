package com.goda.npmoa.presentation_layer.ui.home.new_detail

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ArticleDetailsFragmentProvider {
    @ContributesAndroidInjector(modules = [ArticleDetailsFragmentModule::class])
    abstract fun provideArticleDetailsFragmentFactory(): ArticleDetailsFragment
}