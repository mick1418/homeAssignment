package com.michaellaguerre.symphony.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.michaellaguerre.symphony.data.database.entities.AuthorEntity
import com.michaellaguerre.symphony.data.database.entities.AuthorWithPosts
import com.michaellaguerre.symphony.data.database.entities.PostEntity
import com.michaellaguerre.symphony.data.database.entities.PostWithComments

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAll(): LiveData<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): LiveData<PostEntity>

    @Insert
    fun insertAll(vararg posts: PostEntity)

    @Delete
    fun delete(post: PostEntity)

    @Transaction
    @Query("SELECT * FROM posts WHERE id LIKE :postId LIMIT 1")
    fun getPostWithComments(postId: Int): LiveData<PostWithComments>
}