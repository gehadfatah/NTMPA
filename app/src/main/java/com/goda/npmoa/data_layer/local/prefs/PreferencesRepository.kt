package com.goda.npmoa.data_layer.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesRepository @Inject constructor(private val sharedPreferences: SharedPreferences) :
    PreferencesDataSource