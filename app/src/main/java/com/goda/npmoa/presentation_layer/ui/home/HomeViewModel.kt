package com.goda.npmoa.presentation_layer.ui.home

import android.app.Application
import com.goda.npmoa.data_layer.AppDataManager
import com.goda.npmoa.presentation_layer.ui.base.BaseViewModel

class HomeViewModel(
    application: Application,
    appDataManager: AppDataManager
) : BaseViewModel<Any>(application, appDataManager)