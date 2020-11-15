package com.michaellaguerre.homeassignment.data.database.daos

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.michaellaguerre.homeassignment.data.entities.CommentEntity
import javax.inject.Singleton

/**
 * Room DAO interface containing comment-related database queries
 */
@Singleton
@Dao
interface CommentsDao {

    //**********************************************************************************************
    // READ
    //**********************************************************************************************

    @Query("SELECT * FROM comments")
    fun getAll(): LiveData<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE id LIKE :id LIMIT 1")
    fun getById(id: Int): LiveData<CommentEntity>

    @Query("SELECT * FROM comments WHERE postId IS :postId ORDER BY date ASC")
    fun commentsByPostOrderedByDate(postId: Int): LiveData<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE postId IS :postId ORDER BY date ASC")
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
    fun deleteAll()

    @Query("DELETE FROM comments WHERE postId IS :postId")
    fun deleteAllCommentsForPost(postId: Int)


    //**********************************************************************************************
    // CUSTOM QUERIES
    //**********************************************************************************************

    @Query("SELECT * FROM comments WHERE postId LIKE :postId")
    fun getCommentsForPost(postId: Int): LiveData<List<CommentEntity>>
}