package com.goda.npmoa.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.goda.movieapp.MainCoroutineRule

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
class MoviesRemoteDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

/*
    lateinit var articleRemoteDataSource: ArticleRemoteDataSource


    lateinit var articleService: ArticlesService


    val fakeArticles = listOf(
        Article(
            123344,
            "Hello ",
            123L,
            "shika",
            "hhhh",
            "",
            "12/2/3333",
            "4",
            "",
            "",
            "",
            "",
            "",
            1,
            emptyList()
        )
    )

    val articlesResponse = ArticlesResponse("", 12, fakeArticles, "")


    @Before
    fun init() {

        articleService = mock {
            onBlocking { getArticlesAsync(BuildConfig.API_KEY) } doReturn GlobalScope.async {
                Response.success(articlesResponse)
            }
        }

        articleRemoteDataSource = ArticleRemoteDataSourceImpl(articleService, mainCoroutineRule.coroutineContext)


    }

    @Test
    fun testGetArticle() = runBlocking {

        articleService = mock {
            onBlocking { getArticlesAsync(BuildConfig.API_KEY) } doReturn GlobalScope.async {
                Response.success(articlesResponse)
            }
        }

        articleRemoteDataSource = ArticleRemoteDataSourceImpl(articleService, mainCoroutineRule.coroutineContext)


        // Will be launched in the mainThreadSurrogate dispatcher
        val result = articleRemoteDataSource.getArticles()



        assert(result == articlesResponse.articles)


    }

    @Test(expected = Exception::class)
    fun testThrowArticleException() = runBlocking {

        articleService = mock {
            onBlocking { getArticlesAsync(BuildConfig.API_KEY) } doReturn GlobalScope.async {
                Response.error<ArticlesResponse>(404, null)
            }
        }

        articleRemoteDataSource = ArticleRemoteDataSourceImpl(articleService, mainCoroutineRule.coroutineContext)



        val result = articleRemoteDataSource.getArticles()




    }*/


}