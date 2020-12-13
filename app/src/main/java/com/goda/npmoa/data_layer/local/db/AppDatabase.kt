package com.goda.npmoa.data_layer.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.goda.npmoa.data_layer.local.db.dao.ArticleDao
import com.goda.npmoa.data_layer.model.db.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}