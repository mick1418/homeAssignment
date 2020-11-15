package com.michaellaguerre.homeassignment.data.database.daos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.michaellaguerre.homeassignment.data.database.AppDatabase
import com.michaellaguerre.homeassignment.utilities.getValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RemoteKeyDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var remoteKeyDao: RemoteKeyDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        remoteKeyDao = database.remoteKeyDao()

        runBlocking {
            remoteKeyDao.insertOrReplace(remoteKey1)
            remoteKeyDao.insertOrReplace(remoteKey2)
        }
    }

    @After
    fun closeDb() {
        database.close()
    }


    //**********************************************************************************************
    // TESTS
    //**********************************************************************************************

    @Test
    fun insertNonExistingRemoteKey() {

        var remoteKeysList = getValue(remoteKeyDao.getAll())
        assertThat(remoteKeysList.size, equalTo(2))

        runBlocking {
            remoteKeyDao.insertOrReplace(remoteKey3)
        }

        remoteKeysList = getValue(remoteKeyDao.getAll())
        assertThat(remoteKeysList.size, equalTo(3))
        assertThat(remoteKeysList, contains(remoteKey1, remoteKey2, remoteKey3))
    }

    @Test
    fun insertExistingRemoteKey() {

        var remoteKeysList = getValue(remoteKeyDao.getAll())
        assertThat(remoteKeysList.size, equalTo(2))

        val remoteKey1Reloaded = remoteKey1.copy(nextKey = 3)

        runBlocking {
            remoteKeyDao.insertOrReplace(remoteKey1Reloaded)
        }

        remoteKeysList = getValue(remoteKeyDao.getAll())
        assertThat(remoteKeysList.size, equalTo(2))
        assertThat(remoteKeysList, contains(remoteKey1Reloaded, remoteKey2))

    }

    @Test
    fun getRemoteKeyByExistingQuery() {

        val retrieveRemoteKey1 = runBlocking {
            remoteKeyDao.getRemoteKeyByQuery(remoteKey1.label)
        }

        assertThat(retrieveRemoteKey1, equalTo(remoteKey1))
    }

    @Test
    fun getRemoteKeyByNonExistingQuery() {

        val retrieveRemoteKey3 = runBlocking {
            remoteKeyDao.getRemoteKeyByQuery(remoteKey3.label)
        }

        assertThat(retrieveRemoteKey3, equalTo(null))
    }

    @Test
    fun deleteRemoteKeyByExistingQuery() {

        runBlocking {
            remoteKeyDao.deleteByQuery(remoteKey1.label)
        }

        val remoteKeysList = getValue(remoteKeyDao.getAll())
        assertThat(remoteKeysList.size, equalTo(1))
        assertThat(remoteKeysList, contains(remoteKey2))
    }

    @Test
    fun deleteRemoteKeyByNonExistingQuery() {

        runBlocking {
            remoteKeyDao.deleteByQuery(remoteKey3.label)
        }

        val remoteKeysList = getValue(remoteKeyDao.getAll())
        assertThat(remoteKeysList.size, equalTo(2))
        assertThat(remoteKeysList, contains(remoteKey1, remoteKey2))
    }
}