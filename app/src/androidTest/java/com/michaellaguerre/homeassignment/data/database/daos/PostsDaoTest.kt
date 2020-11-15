package com.michaellaguerre.homeassignment.data.database.daos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.michaellaguerre.homeassignment.data.database.AppDatabase
import com.michaellaguerre.homeassignment.utilities.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.*
import org.junit.*
import org.junit.Assert.assertThat
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostsDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var postsDao: PostsDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        postsDao = database.postDao()

        // Insert the test data in random order
        postsDao.insertAll(testPostsAuthor1NotSorted)
    }

    @After
    fun closeDb() {
        database.close()
    }


    //**********************************************************************************************
    // TESTS
    //**********************************************************************************************

    @Test
    fun getAllPosts() {
        val postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(3))

        // Ensure posts list contains all 3 test posts
        assertThat(postsList, contains(post1author1, post2author1, post3author1))
    }

    @Test
    fun getExistingPostById() {
        val comment = getValue(postsDao.getById(post1author1.id))
        assertThat(comment, equalTo(post1author1))
    }

    @Test
    fun getNonExistingPostById() {
        val comment = getValue(postsDao.getById(42))
        assertThat(comment, equalTo(null))
    }

    @Test
    fun getPostsOrderedByDateDesc() {
        val postsList = getValue(postsDao.postsByAuthorOrderedByDateDesc(1))
        assertThat(postsList.size, equalTo(3))

        // Ensure posts list is sorted by date desc
        assertThat(postsList[0], equalTo(post3author1))
        assertThat(postsList[1], equalTo(post2author1))
        assertThat(postsList[2], equalTo(post1author1))
    }

    @Ignore("Do not know for now how to test a paging source :/")
    @Test
    fun getPostsOrderedByDateDescPagingSource() {
        Assert.assertTrue(false)
    }

    @Test
    fun insertPostWithNonExistingIdShouldAdd() {
        postsDao.insert(post1author2)

        // Check the table has 4 elements
        val postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(4))

        // Ensure posts list contains all 4 test posts
        assertThat(
            postsList,
            contains(post1author1, post2author1, post3author1, post1author2)
        )
    }

    @Test
    fun insertPostWithExistingIdShouldReplace() {

        val post1Author1Reloaded = post1author1.copy(body = "post1Author1Reloaded body")
        postsDao.insert(post1Author1Reloaded)

        // Check the table has 3 elements
        val postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(3))

        // Check the insert element has replaced the previous one
        val comment = getValue(postsDao.getById(post1author1.id))
        assertThat(comment.id, equalTo(comment1post1.id))
        assertThat(comment.body, equalTo(post1Author1Reloaded.body))
    }

    @Test
    fun insertPostsWithSomeExisting() {

        val post1Author1Reloaded = post1author1.copy(body = "post1Author1Reloaded body")
        val postsToInsert = arrayListOf(post1Author1Reloaded, post1author2, post2author2)
        postsDao.insertAll(postsToInsert)

        // Check the table has 5 elements as post1Author1Reloaded already exists and will be replaced
        val postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(5))

        // Check the table contains the right posts
        assertThat(
            postsList,
            contains(post1Author1Reloaded, post2author1, post3author1, post1author2, post2author2)
        )
    }

    @Test
    fun deleteExistingPost() {
        // Check the table has 3 elements
        var postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(3))

        // Then delete one comment
        postsDao.delete(post1author1)

        // Check the table has now 2 items
        postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(2))
        assertThat(postsList, not(contains(post1author1)))
    }

    @Test
    fun deleteNonExistingPost() {
        // Check the table has 3 elements
        var postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(3))

        // Then delete one comment
        postsDao.delete(post1author2)

        // Check the table has still 3 items
        postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(3))
    }

    @Test
    fun deleteAllPosts() {
        // Check the table has 3 elements
        var postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(3))

        // Then delete everything
        postsDao.deleteAll()

        // Check the table is now empty
        postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(0))
    }

    @Test
    fun getAllPostsForExistingPost() {

        // Add author 2 posts to the base (author 1 posts already added)
        postsDao.insertAll(testPostsAuthor2)

        // Check the table has 6 elements
        val postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(6))

        val author1Posts = getValue(postsDao.getPostsForAuthor(1))

        // Check all the posts are in the list
        assertThat(author1Posts, contains(post1author1, post2author1, post3author1))
        assertThat(author1Posts.size, equalTo(3))
    }

    @Test
    fun getAllPostsForNonExistingPost() {

        val author42Posts = getValue(postsDao.getPostsForAuthor(42))

        // Check that nothing is returned
        assertThat(author42Posts.size, equalTo(0))
    }


    @Test
    fun deleteAllPostsForExistingPost() {

        // Add author 2 posts to the base (author 1 posts already added)
        postsDao.insertAll(testPostsAuthor2)

        // Check the table has 6 elements
        var postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(6))

        postsDao.deleteAllPostsForAuthor(1)

        // Check the table has 3 elements
        postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(3))
        assertThat(postsList, not(contains(post1author1, post2author1, post3author1)))
    }


    @Test
    fun deleteAllPostsForNonExistingPost() {

        // Add author 2 posts to the base (author 1 posts already added)
        postsDao.insertAll(testPostsAuthor2)

        // Check the table has 6 elements
        var postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(6))

        postsDao.deleteAllPostsForAuthor(42)

        // Check the table has still 6 elements
        postsList = getValue(postsDao.getAll())
        assertThat(postsList.size, equalTo(6))
    }
}