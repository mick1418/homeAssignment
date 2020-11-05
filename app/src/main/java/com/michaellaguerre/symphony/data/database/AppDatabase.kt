package com.michaellaguerre.symphony.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.michaellaguerre.symphony.data.database.daos.AuthorDao
import com.michaellaguerre.symphony.data.database.daos.CommentDao
import com.michaellaguerre.symphony.data.database.daos.PostDao
import com.michaellaguerre.symphony.data.database.entities.AuthorEntity
import com.michaellaguerre.symphony.data.database.entities.CommentEntity
import com.michaellaguerre.symphony.data.database.entities.PostEntity

@Database(
    entities = arrayOf(AuthorEntity::class, PostEntity::class, CommentEntity::class),
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authorDao(): AuthorDao
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
}