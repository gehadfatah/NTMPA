package com.goda.npmoa.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.test.runBlockingTest

import com.goda.npmoa.data_layer.local.db.AppDatabase
import com.goda.npmoa.data_layer.model.db.Article
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ArticleDaoTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase


    @Before
    fun init_DB(){
        database= Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),AppDatabase::class.java).allowMainThreadQueries().build()

    }


    @After
    fun close_Db() {
        database.close()
    }
    @Test
    fun insertTaskAndGetById() = runBlockingTest {
        // GIVEN - Insert a Article.
        val task = Article(
            section = "goda.",
            abstractX = "0",
            byline = "",
            title = "new article",
            id = 5,
            imageUrl = "j a",
            coverImageUrl = "",
            publishedDate = "",
            url = ""
            )
        database.articleDao().insert(task)

        // WHEN - Get the Article by id from the database.
        val loaded = database.articleDao().findById(task.id)

        // THEN - The loaded data contains the expected values.
        assertThat<Article>(loaded as Article, notNullValue())
        assertThat(loaded.id, `is`(task.id))
        assertThat(loaded.title, `is`(task.title))
        assertThat(loaded.section, `is`(task.section))
        assertThat(loaded.imageUrl, `is`(task.imageUrl))
    }


}