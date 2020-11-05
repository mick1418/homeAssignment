package com.michaellaguerre.symphony.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.michaellaguerre.symphony.data.entities.CommentEntity

@Dao
interface CommentDao {

    //**********************************************************************************************
    // READ
    //**********************************************************************************************

    @Query("SELECT * FROM comments")
    fun getAll(): LiveData<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): LiveData<CommentEntity>


    //**********************************************************************************************
    // INSERT
    //**********************************************************************************************

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comment: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(comments: List<CommentEntity>)


    //**********************************************************************************************
    // DELETE
    //**********************************************************************************************

    @Delete
    fun delete(comment: CommentEntity)
}