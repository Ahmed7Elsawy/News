package com.elsawy.ahmed.news.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elsawy.ahmed.news.data.db.entity.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
abstract class ArticleRoomDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile private var instance: ArticleRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ArticleRoomDatabase::class.java, "top_articles.db")
                .build()
    }

}