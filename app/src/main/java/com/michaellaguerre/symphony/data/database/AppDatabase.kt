package com.michaellaguerre.symphony.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.michaellaguerre.symphony.data.database.daos.AuthorsDao
import com.michaellaguerre.symphony.data.database.daos.CommentsDao
import com.michaellaguerre.symphony.data.database.daos.PostsDao
import com.michaellaguerre.symphony.data.database.daos.RemoteKeyDao
import com.michaellaguerre.symphony.data.entities.AuthorEntity
import com.michaellaguerre.symphony.data.entities.CommentEntity
import com.michaellaguerre.symphony.data.entities.PostEntity
import com.michaellaguerre.symphony.data.entities.RemoteKey

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