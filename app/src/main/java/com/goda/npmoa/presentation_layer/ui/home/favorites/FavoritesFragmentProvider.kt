package com.goda.npmoa.presentation_layer.ui.home.favorites

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoritesFragmentProvider {
    @ContributesAndroidInjector(modules = [FavoritesFragmentModule::class])
    abstract fun provideFavoritesFragmentFactory(): FavoritesFragment
}