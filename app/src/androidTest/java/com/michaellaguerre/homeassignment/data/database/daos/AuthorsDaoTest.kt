package com.michaellaguerre.homeassignment.data.database.daos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.michaellaguerre.homeassignment.data.database.AppDatabase
import com.michaellaguerre.homeassignment.utilities.*
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.not
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthorsDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var authorsDao: AuthorsDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        authorsDao = database.authorDao()

        // Insert the test data in random order
        authorsDao.insertAll(testAuthorsNotSorted)
    }

    @After
    fun closeDb() {
        database.close()
    }


    //**********************************************************************************************
    // TESTS
    //**********************************************************************************************

    @Test
    fun getAllAuthors() {
        val authorsList = getValue(authorsDao.getAll())
        assertThat(authorsList.size, equalTo(4))

        // Ensure authors list contains all 4 test authors
        assertThat(authorsList, contains(authorA, authorB, authorC, authorD))
    }

    @Test
    fun getExistingAuthorById() {
        val author = getValue(authorsDao.getById(authorA.id))
        assertThat(author, equalTo(authorA))
    }

    @Test
    fun getNonExistingAuthorById() {
        val author = getValue(authorsDao.getById(42))
        assertThat(author, equalTo(null))
    }

    @Test
    fun getAuthorsOrderedByName() {
        val authorsList = getValue(authorsDao.authorsOrderedByName())
        assertThat(authorsList.size, equalTo(4))

        // Ensure authors list is sorted by name
        assertThat(authorsList[0], equalTo(authorA))
        assertThat(authorsList[1], equalTo(authorB))
        assertThat(authorsList[2], equalTo(authorC))
        assertThat(authorsList[3], equalTo(authorD))
    }

    @Ignore("Do not know for now how to test a paging source :/")
    @Test
    fun getAuthorsOrderedByNamePagingSource() {
        Assert.assertTrue(false)
    }

    @Test
    fun insertAuthorWithNonExistingIdShouldAdd() {
        authorsDao.insert(authorE)

        // Check the table has 5 elements
        val authorsList = getValue(authorsDao.getAll())
        assertThat(authorsList.size, equalTo(5))

        // Ensure authors list contains all 5 test authors
        assertThat(
            authorsList,
            contains(authorA, authorB, authorC, authorD, authorE)
        )
    }

    @Test
    fun insertAuthorWithExistingIdShouldReplace() {

        val authorAReloaded = authorA.copy(name = "Author A Reloaded")
        authorsDao.insert(authorAReloaded)

        // Check the table has 4 elements
        val authorsList = getValue(authorsDao.getAll())
        assertThat(authorsList.size, equalTo(4))

        // Check the table has 4 elements
        val author = getValue(authorsDao.getById(authorA.id))
        assertThat(author.id, equalTo(authorA.id))
        assertThat(author.name, equalTo(authorAReloaded.name))
    }

    @Test
    fun insertAuthorsWithSomeExisting() {

        val authorCReloaded = authorC.copy(name = "Author C Reloaded")
        val authorsToInsert = arrayListOf(authorCReloaded, authorE, authorF)
        authorsDao.insertAll(authorsToInsert)

        // Check the table has 6 elements as authorC already exists and will be replaced
        val authorsList = getValue(authorsDao.getAll())
        assertThat(authorsList.size, equalTo(6))

        // Check the table contains the right authors
        assertThat(
            authorsList,
            contains(authorA, authorB, authorCReloaded, authorD, authorE, authorF)
        )
    }

    @Test
    fun deleteAuthor() {
        // Check the table has 4 elements
        var authorsList = getValue(authorsDao.getAll())
        assertThat(authorsList.size, equalTo(4))

        // Then delete everything
        authorsDao.delete(authorC)

        // Check the table has now 3 items
        authorsList = getValue(authorsDao.getAll())
        assertThat(authorsList.size, equalTo(3))
        assertThat(authorsList, not(contains(authorC)))
    }

    @Test
    fun deleteAllAuthors() {
        // Check the table has 4 elements
        var authorsList = getValue(authorsDao.getAll())
        assertThat(authorsList.size, equalTo(4))

        // Then delete everything
        authorsDao.deleteAll()

        // Check the table is now empty
        authorsList = getValue(authorsDao.getAll())
        assertThat(authorsList.size, equalTo(0))
    }
}