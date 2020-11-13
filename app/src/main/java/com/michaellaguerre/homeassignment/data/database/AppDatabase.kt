package com.michaellaguerre.homeassignment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.michaellaguerre.homeassignment.data.database.daos.AuthorsDao
import com.michaellaguerre.homeassignment.data.database.daos.CommentsDao
import com.michaellaguerre.homeassignment.data.database.daos.PostsDao
import com.michaellaguerre.homeassignment.data.database.daos.RemoteKeyDao
import com.michaellaguerre.homeassignment.data.entities.AuthorEntity
import com.michaellaguerre.homeassignment.data.entities.CommentEntity
import com.michaellaguerre.homeassignment.data.entities.PostEntity
import com.michaellaguerre.homeassignment.data.entities.RemoteKey

/**
 * Main Room database
 */
@Database(
    entities = [AuthorEntity::class, PostEntity::class, CommentEntity::class, RemoteKey::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authorDao(): AuthorsDao
    abstract fun postDao(): PostsDao
    abstract fun commentDao(): CommentsDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}