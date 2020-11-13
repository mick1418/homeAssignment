package com.michaellaguerre.homeassignment.data.database.daos

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.michaellaguerre.homeassignment.data.entities.PostEntity

/**
 * Room DAO interface containing post-related database queries
 */
@Dao
interface PostsDao {

    //**********************************************************************************************
    // READ
    //**********************************************************************************************

    @Query("SELECT * FROM posts")
    fun getAll(): LiveData<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): LiveData<PostEntity>

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
    suspend fun deleteAll()

    @Query("DELETE FROM posts WHERE authorId LIKE :authorId")
    suspend fun deleteAllPostsForAuthor(authorId: Int)


    //**********************************************************************************************
    // CUSTOM QUERIES
    //**********************************************************************************************

    @Query("SELECT * FROM posts WHERE authorId LIKE :authorId")
    fun getPostsForAuthor(authorId: Int): LiveData<List<PostEntity>>
}