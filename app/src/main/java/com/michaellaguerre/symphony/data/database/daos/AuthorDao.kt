package com.michaellaguerre.symphony.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.michaellaguerre.symphony.data.database.entities.AuthorEntity
import com.michaellaguerre.symphony.data.database.entities.AuthorWithPosts

@Dao
interface AuthorDao {

    @Query("SELECT * FROM authors")
    fun getAll(): LiveData<List<AuthorEntity>>

    @Query("SELECT * FROM authors WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): LiveData<AuthorEntity>

    @Insert
    fun insertAll(vararg authors: AuthorEntity)

    @Delete
    fun delete(author: AuthorEntity)

    @Transaction
    @Query("SELECT * FROM authors WHERE id LIKE :authorId LIMIT 1")
    fun getAuthorWithPosts(authorId: Int): LiveData<AuthorWithPosts>
}