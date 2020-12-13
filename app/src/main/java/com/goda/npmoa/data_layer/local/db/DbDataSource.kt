package com.goda.npmoa.data_layer.local.db

import androidx.lifecycle.LiveData
import com.goda.npmoa.data_layer.model.db.Article
import com.goda.npmoa.data_layer.model.Result

interface DbDataSource {
    suspend fun insertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    suspend fun findById(id: Long): Result<Article>
    fun allArticles(): LiveData<List<Article>>
}