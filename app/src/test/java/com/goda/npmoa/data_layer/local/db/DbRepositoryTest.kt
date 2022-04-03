package com.goda.npmoa.data_layer.local.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.goda.movieapp.MainCoroutineRule
import com.goda.newstk.common.mock
import com.goda.npmoa.common.articlesResponse
import com.goda.npmoa.data_layer.local.db.dao.ArticleDao
import com.godaMeal.meals.LiveDataTestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DbRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var dbRepository: DbDataSource
    //lateinit var db: AppDatabase

    @Mock
    lateinit var tagsDao: ArticleDao
    @Before
    fun init() {
       // db = mock()
        MockitoAnnotations.openMocks(this)

        //dbRepository = DbRepository(db)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given tag name when gettagbYtagname success in remote return success list `() =
        runBlocking {
            //arrange
           val dp= mock<AppDatabase> {
               onBlocking {

                   tagsDao.loadAll()
               } doReturn
                       liveData { articlesResponse.articles }
           }

           // Mockito.`when`(db.articleDao().loadAll()).thenReturn(liveData { articlesResponse.articles })
             dbRepository=DbRepository(dp)
            //act
            val result = dbRepository.allArticles()

            //assert
            assert(LiveDataTestUtil.getValue(result) == articlesResponse.articles)


        }

    @Test(expected = Exception::class)
    fun `Given tagname when it Desserts then return failed result`(): Unit = runBlocking {
        val dp= mock<AppDatabase> {
            onBlocking {

                tagsDao.loadAll()
            } doThrow(Exception("error"))
        }
       // Mockito.doThrow(Exception("error")).`when`(db.articleDao().loadAll())
        dbRepository=DbRepository(dp)

        dbRepository.allArticles()


    }

}