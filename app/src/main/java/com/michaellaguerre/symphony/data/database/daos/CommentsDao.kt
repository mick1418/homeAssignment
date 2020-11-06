package com.michaellaguerre.symphony.data.database.daos

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.michaellaguerre.symphony.data.entities.CommentEntity
import com.michaellaguerre.symphony.data.entities.PostEntity

@Dao
interface CommentsDao {

    //**********************************************************************************************
    // READ
    //**********************************************************************************************

    @Query("SELECT * FROM comments")
    fun getAll(): LiveData<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): LiveData<CommentEntity>

    @Query("SELECT * FROM comments WHERE postId IS :postId ORDER BY date DESC")
    fun commentsByPostPagingSource(postId: Int): PagingSource<Int, CommentEntity>


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

    @Query("DELETE FROM comments")
    suspend fun deleteAll()

    @Query("DELETE FROM comments WHERE postId IS :postId")
    suspend fun deleteAllCommentsForPost(postId: Int)


    //**********************************************************************************************
    // CUSTOM QUERIES
    //**********************************************************************************************

    @Query("SELECT * FROM comments WHERE postId LIKE :postId")
    fun getCommentsForPost(postId: Int): LiveData<List<CommentEntity>>
}