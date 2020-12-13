package com.goda.npmoa.presentation_layer.ui.home.favorites

import dagger.Module
import dagger.Provides
import java.util.*

@Module
class FavoritesFragmentModule {
    @Provides
    fun provideFavoritesAdapter(): FavoritesAdapter {
        return FavoritesAdapter(ArrayList())
    }
}