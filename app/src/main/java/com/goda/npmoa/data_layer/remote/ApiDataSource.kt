package com.goda.npmoa.data_layer.remote

import com.goda.npmoa.data_layer.model.Result
import com.goda.npmoa.data_layer.model.api.ArticlesResponse

interface ApiDataSource {
    suspend fun getArticles(period: Int): Result<ArticlesResponse>
}