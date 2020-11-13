package com.michaellaguerre.homeassignment.core.di.modules

import android.content.Context
import androidx.room.Room
import com.michaellaguerre.homeassignment.data.database.AppDatabase
import com.michaellaguerre.homeassignment.data.database.daos.AuthorsDao
import com.michaellaguerre.homeassignment.data.database.daos.CommentsDao
import com.michaellaguerre.homeassignment.data.database.daos.PostsDao
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
        ).fallbackToDestructiveMigration().build()
    }

    /**
     * Provides an instance of the [AuthorsDao] from the Room database
     *
     * @param appDatabase the Room [AppDatabase]
     * @return an instance of [AuthorsDao]
     */
    @Provides
    fun provideAuthorsDao(appDatabase: AppDatabase): AuthorsDao {
        return appDatabase.authorDao()
    }

    /**
     * Provides an instance of the [PostsDao] from the Room database
     *
     * @param appDatabase the Room [AppDatabase]
     * @return an instance of [PostsDao]
     */
    @Provides
    fun providePostsDao(appDatabase: AppDatabase): PostsDao {
        return appDatabase.postDao()
    }

    /**
     * Provides an instance of the [CommentsDao] from the Room database
     *
     * @param appDatabase the Room [AppDatabase]
     * @return an instance of [CommentsDao]
     */
    @Provides
    fun provideCommentsDao(appDatabase: AppDatabase): CommentsDao {
        return appDatabase.commentDao()
    }
}