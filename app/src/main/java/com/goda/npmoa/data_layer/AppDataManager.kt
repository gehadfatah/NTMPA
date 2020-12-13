package com.goda.npmoa.data_layer

import com.goda.npmoa.data_layer.local.db.DbRepository
import com.goda.npmoa.data_layer.local.prefs.PreferencesRepository
import com.goda.npmoa.data_layer.remote.ApiRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dbRepository: DbRepository,
    private val preferencesRepository: PreferencesRepository
) : DataManager {
    fun getApiRepository(): ApiRepository {
        return apiRepository
    }

    fun getDbRepository(): DbRepository {
        return dbRepository
    }

    fun getPreferencesRepository(): PreferencesRepository {
        return preferencesRepository
    }
}
