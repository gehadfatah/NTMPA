/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.goda.npmoa

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room

import com.goda.npmoa.data_layer.local.db.AppDatabase
import com.goda.npmoa.data_layer.local.db.DbRepository
import com.goda.npmoa.data_layer.remote.ApiRepository
import kotlinx.coroutines.runBlocking

/**
 * A Service Locator for the [TasksRepository]. This is the prod version, with a
 * the "real" [TasksRemoteDataSource].
 */
object ServiceLocator {

    private val lock = Any()
    private var database: AppDatabase? = null
  /*  @Volatile
    var tasksRepository: ApiRepository? = null
        @VisibleForTesting set*/
    @Volatile
    var dpRepository: DbRepository? = null
        @VisibleForTesting set
    fun provideTasksRepository(context: Context): DbRepository {
        synchronized(this) {
            return dpRepository ?: createTasksRepository(context)
        }
    }

  /*  private fun createTasksRepository(): ApiRepository {
        val newRepo = ApiRepository()
        tasksRepository = newRepo
        return newRepo
    }*/
    private fun createTasksRepository(context: Context): DbRepository {
        val newRepo = DbRepository(database ?: createDataBase(context))
        dpRepository = newRepo
        return newRepo
    }
/*
    private fun createTaskLocalDataSource(context: Context): TasksDataSource {
        val database = database ?: createDataBase(context)
        return TasksLocalDataSource(database.articleDao())
    }
*/

    private fun createDataBase(context: Context): AppDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "article.db"
        ).build()
        database = result
        return result
    }

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            runBlocking {
                //TasksRemoteDataSource.deleteAllTasks()
            }
            // Clear all data to avoid test pollution.
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            dpRepository = null
        }
    }
}
