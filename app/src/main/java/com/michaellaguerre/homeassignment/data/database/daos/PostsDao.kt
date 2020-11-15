package com.michaellaguerre.homeassignment.data.database.daos

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.michaellaguerre.homeassignment.data.entities.PostEntity
import javax.inject.Singleton

/**
 * Room DAO interface containing post-related database queries
 */
@Singleton
@Dao
interface PostsDao {

    //**********************************************************************************************
    // READ
    //**********************************************************************************************

    @Query("SELECT * FROM posts")
    fun getAll(): LiveData<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE id LIKE :id LIMIT 1")
    fun getById(id: Int): LiveData<PostEntity>

    @Query("SELECT * FROM posts WHERE authorId IS :authorId ORDER BY date DESC")
    fun postsByAuthorOrderedByDateDesc(authorId: Int): LiveData<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE authorId IS :authorId ORDER BY date DESC")
    fun postsByAuthorPagingSource(authorId: Int): PagingSource<Int, PostEntity>


    //**********************************************************************************************
    // INSERT
    //**********************************************************************************************

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostEntity)


    //**********************************************************************************************
    // DELETE
    //**********************************************************************************************

    @Delete
    fun delete(post: PostEntity)

    @Query("DELETE FROM posts")
    fun deleteAll()

    @Query("DELETE FROM posts WHERE authorId LIKE :authorId")
    fun deleteAllPostsForAuthor(authorId: Int)


    //**********************************************************************************************
    // CUSTOM QUERIES
    //**********************************************************************************************

    @Query("SELECT * FROM posts WHERE authorId LIKE :authorId")
    fun getPostsForAuthor(authorId: Int): LiveData<List<PostEntity>>
}