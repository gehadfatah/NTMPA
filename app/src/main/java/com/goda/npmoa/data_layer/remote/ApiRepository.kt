package com.goda.npmoa.data_layer.remote

import com.goda.npmoa.data_layer.model.Result
import com.goda.npmoa.data_layer.model.api.ArticlesResponse
import com.goda.npmoa.data_layer.remote.network.ApiService
import com.goda.npmoa.di.ApiInfo
import com.goda.npmoa.presentation_layer.common.espresso_idling.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(private val apiService: ApiService, @param:ApiInfo private val apiKey: String) :
    ApiDataSource {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getArticles(period: Int): Result<ArticlesResponse> =
        wrapEspressoIdlingResource {

            withContext(ioDispatcher) {
                try {
                    val articlesResponse = apiService.getArticles(period, apiKey)
                    return@withContext Result.Success(articlesResponse)
                } catch (e: Exception) {
                    return@withContext Result.Error(e.localizedMessage)
                }
            }
        }

}