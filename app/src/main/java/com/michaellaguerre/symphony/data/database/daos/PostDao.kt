package com.michaellaguerre.symphony.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.michaellaguerre.symphony.data.database.PostWithComments
import com.michaellaguerre.symphony.data.entities.PostEntity

@Dao
interface PostDao {

    //**********************************************************************************************
    // READ
    //**********************************************************************************************

    @Query("SELECT * FROM posts")
    fun getAll(): LiveData<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): LiveData<PostEntity>


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


    //**********************************************************************************************
    // CUSTOM QUERIES
    //**********************************************************************************************

    @Transaction
    @Query("SELECT * FROM posts WHERE id LIKE :postId LIMIT 1")
    fun getPostWithComments(postId: Int): LiveData<PostWithComments>
}