package com.goda.npmoa.data_layer.local.db

import androidx.lifecycle.LiveData
import com.goda.npmoa.data_layer.model.Result
import com.goda.npmoa.data_layer.model.db.Article
import com.goda.npmoa.presentation_layer.common.espresso_idling.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbRepository @Inject constructor(private val mAppDatabase: AppDatabase) :
    DbDataSource {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun insertArticle(article: Article) {
        wrapEspressoIdlingResource {

            withContext(ioDispatcher) {
                mAppDatabase.articleDao().insert(article)
            }
        }
    }

    override suspend fun deleteArticle(article: Article) {
        wrapEspressoIdlingResource {

            withContext(ioDispatcher) {
                mAppDatabase.articleDao().delete(article)
            }
        }
    }

    override suspend fun findById(id: Long): Result<Article> = withContext(ioDispatcher) {
        wrapEspressoIdlingResource {

            try {
                val article = mAppDatabase.articleDao().findById(id)
                return@withContext Result.Success(article)
            } catch (e: Exception) {
                return@withContext Result.Error(e.localizedMessage)
            }
        }

    }

    override fun allArticles(): LiveData<List<Article>> {
      //  wrapEspressoIdlingResource {

            return mAppDatabase.articleDao().loadAll()
      //  }
    }

}