package com.michaellaguerre.homeassignment.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.michaellaguerre.homeassignment.data.entities.AuthorEntity
import com.michaellaguerre.homeassignment.data.entities.RemoteKey
import javax.inject.Singleton

/**
 * Room DAO interface containing remotekey-related database queries
 */
@Singleton
@Dao
interface RemoteKeyDao {


    //**********************************************************************************************
    // INSERT
    //**********************************************************************************************

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    //**********************************************************************************************
    // READ
    //**********************************************************************************************

    @Query("SELECT * FROM remote_keys ORDER BY label ASC")
    fun getAll(): LiveData<List<RemoteKey>>


    @Query("SELECT * FROM remote_keys WHERE label = :query")
    suspend fun getRemoteKeyByQuery(query: String): RemoteKey

    //**********************************************************************************************
    // DELETE
    //**********************************************************************************************

    @Query("DELETE FROM remote_keys WHERE label = :query")
    suspend fun deleteByQuery(query: String)
}