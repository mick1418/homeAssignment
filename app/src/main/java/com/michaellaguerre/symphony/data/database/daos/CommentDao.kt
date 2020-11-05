package com.michaellaguerre.symphony.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.michaellaguerre.symphony.data.database.entities.AuthorEntity
import com.michaellaguerre.symphony.data.database.entities.CommentEntity
import com.michaellaguerre.symphony.data.database.entities.PostEntity

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments")
    fun getAll(): LiveData<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): LiveData<CommentEntity>

    @Insert
    fun insertAll(vararg comments: CommentEntity)

    @Delete
    fun delete(comment: CommentEntity)
}