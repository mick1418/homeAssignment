package com.michaellaguerre.symphony.core.di.modules

import android.content.Context
import androidx.room.Room
import com.michaellaguerre.symphony.data.database.AppDatabase
import com.michaellaguerre.symphony.data.database.daos.AuthorDao
import com.michaellaguerre.symphony.data.database.daos.CommentDao
import com.michaellaguerre.symphony.data.database.daos.PostDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule() {

    /**
     * Provides a singleton instance of the Room database
     *
     * @param context a [Context]
     * @return an instance of [AppDatabase]
     */
    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "room-database"
        ).build()
    }

    /**
     * Provides an instance of the [AuthorDao] from the Room database
     *
     * @param appDatabase the Room [AppDatabase]
     * @return an instance of [AuthorDao]
     */
    @Provides
    fun provideAuthorDao(appDatabase: AppDatabase): AuthorDao {
        return appDatabase.authorDao()
    }

    /**
     * Provides an instance of the [PostDao] from the Room database
     *
     * @param appDatabase the Room [AppDatabase]
     * @return an instance of [PostDao]
     */
    @Provides
    fun providePostDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }

    /**
     * Provides an instance of the [CommentDao] from the Room database
     *
     * @param appDatabase the Room [AppDatabase]
     * @return an instance of [CommentDao]
     */
    @Provides
    fun provideCommentDao(appDatabase: AppDatabase): CommentDao {
        return appDatabase.commentDao()
    }
}