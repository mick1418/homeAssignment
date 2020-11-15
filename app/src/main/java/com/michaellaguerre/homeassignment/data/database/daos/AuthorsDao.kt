package com.michaellaguerre.homeassignment.data.database.daos

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.michaellaguerre.homeassignment.data.entities.AuthorEntity
import javax.inject.Singleton

/**
 * Room DAO interface containing author-related database queries
 */
@Singleton
@Dao
interface AuthorsDao {


    //**********************************************************************************************
    // READ
    //**********************************************************************************************

    @Query("SELECT * FROM authors")
    fun getAll(): LiveData<List<AuthorEntity>>

    @Query("SELECT * FROM authors WHERE id LIKE :id LIMIT 1")
    fun getById(id: Int): LiveData<AuthorEntity>

    @Query("SELECT * FROM authors ORDER BY name ASC")
    fun authorsOrderedByName(): LiveData<List<AuthorEntity>>

    @Query("SELECT * FROM authors ORDER BY name ASC")
    fun authorsPagingSource(): PagingSource<Int, AuthorEntity>


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

    @Query("DELETE FROM authors")
    fun deleteAll()
}