package com.michaellaguerre.symphony.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.michaellaguerre.symphony.data.entities.AuthorEntity
import com.michaellaguerre.symphony.data.database.AuthorWithPosts
import javax.inject.Singleton

@Singleton
@Dao
interface AuthorDao {


    //**********************************************************************************************
    // READ
    //**********************************************************************************************

    @Query("SELECT * FROM authors")
    fun getAll(): LiveData<List<AuthorEntity>>

    @Query("SELECT * FROM authors WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): LiveData<AuthorEntity>


    //**********************************************************************************************
    // INSERT
    //**********************************************************************************************

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(author: AuthorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(authors: List<AuthorEntity>)


    //**********************************************************************************************
    // DELETE
    //**********************************************************************************************

    @Delete
    fun delete(author: AuthorEntity)


    //**********************************************************************************************
    // CUSTOM QUERIES
    //**********************************************************************************************

    @Transaction
    @Query("SELECT * FROM authors WHERE id LIKE :authorId LIMIT 1")
    fun getAuthorWithPosts(authorId: Int): LiveData<AuthorWithPosts>
}