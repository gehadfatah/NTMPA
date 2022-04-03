package com.goda.npmoa.data_layer.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.goda.movieapp.MainCoroutineRule
import com.goda.npmoa.common.articlesResponse
import com.goda.npmoa.data_layer.model.Result
import com.goda.npmoa.data_layer.remote.network.ApiService
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
@ExperimentalCoroutinesApi

class ApiRepositoryTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    lateinit var repository: ApiDataSource

    @Mock
    lateinit var service: ApiService
    val apiKey = ""

    @Before
    fun before() {
        //service = mock()
        MockitoAnnotations.openMocks(this)

        repository = ApiRepository(service, apiKey)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given service with mock articles response then return this success result to repo `() =
      runBlocking {
            //arrange

            Mockito.`when`(service.getArticles(7, apiKey)).thenReturn(articlesResponse)

            //act
            val result = repository.getArticles(7)


            //assert

            assert(result is Result.Success)
            assert((result as Result.Success).data== articlesResponse)


        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test(expected = Exception::class)
    fun `Given service with mock articles response then return this error result to repo with message `() =
        runBlocking {
            //arrange

            Mockito.`when`(service.getArticles(7, apiKey)).thenThrow(Exception("error"))

            //act
            val result = repository.getArticles(7)


            //assert

            assert(result is Result.Error)
            assert((result as Result.Error).message== "error")


        }
}