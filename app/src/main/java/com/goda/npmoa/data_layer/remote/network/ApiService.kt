package com.goda.npmoa.data_layer.remote.network

import com.goda.npmoa.data_layer.model.api.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiEndPoint.ENDPOINT_Most_NEWS)
    suspend fun getMostArticles(@Path("period") period: Int, @Query("api-key") apiKey: String): ArticlesResponse

    @GET(ApiEndPoint.ENDPOINT_All_Sections_ARTICLES)
    suspend fun getArticles(@Path("period") period: Int, @Query("api-key") apiKey: String): ArticlesResponse

}