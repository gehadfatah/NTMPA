package com.goda.npmoa.presentation_layer.ui.home.new_detail.di

import com.goda.npmoa.presentation_layer.ui.home.new_detail.fragments.ArticleDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ArticleDetailsFragmentProvider {
    @ContributesAndroidInjector(modules = [ArticleDetailsFragmentModule::class])
    abstract fun provideArticleDetailsFragmentFactory(): ArticleDetailsFragment
}