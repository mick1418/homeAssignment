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
class CommentsDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var commentsDao: CommentsDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        commentsDao = database.commentDao()

        // Insert the test data in random order
        commentsDao.insertAll(testCommentsPost1NotSorted)
    }

    @After
    fun closeDb() {
        database.close()
    }


    //**********************************************************************************************
    // TESTS
    //**********************************************************************************************

    @Test
    fun getAllComments() {
        val commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(3))

        // Ensure comments list contains all 3 test comments
        assertThat(commentsList, contains(comment1post1, comment2post1, comment3post1))
    }

    @Test
    fun getExistingCommentById() {
        val comment = getValue(commentsDao.getById(comment1post1.id))
        assertThat(comment, equalTo(comment1post1))
    }

    @Test
    fun getNonExistingCommentById() {
        val comment = getValue(commentsDao.getById(42))
        assertThat(comment, equalTo(null))
    }

    @Test
    fun getCommentsOrderedByDateAsc() {
        val commentsList = getValue(commentsDao.commentsByPostOrderedByDate(1))
        assertThat(commentsList.size, equalTo(3))

        // Ensure comments list is sorted by date asc
        assertThat(commentsList[0], equalTo(comment1post1))
        assertThat(commentsList[1], equalTo(comment2post1))
        assertThat(commentsList[2], equalTo(comment3post1))
    }

    @Ignore("Do not know for now how to test a paging source :/")
    @Test
    fun getCommentsOrderedByDateAscPagingSource() {
        Assert.assertTrue(false)
    }

    @Test
    fun insertCommentWithNonExistingIdShouldAdd() {
        commentsDao.insert(comment1post2)

        // Check the table has 4 elements
        val commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(4))

        // Ensure comments list contains all 4 test comments
        assertThat(
            commentsList,
            contains(comment1post1, comment2post1, comment3post1, comment1post2)
        )
    }

    @Test
    fun insertCommentWithExistingIdShouldReplace() {

        val comment1post1Reloaded = comment1post1.copy(body = "comment1post1Reloaded body")
        commentsDao.insert(comment1post1Reloaded)

        // Check the table has 3 elements
        val commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(3))

        // Check the insert element has replaced the previous one
        val comment = getValue(commentsDao.getById(comment1post1.id))
        assertThat(comment.id, equalTo(comment1post1.id))
        assertThat(comment.body, equalTo(comment1post1Reloaded.body))
    }

    @Test
    fun insertCommentsWithSomeExisting() {

        val comment3post1Reloaded = comment3post1.copy(body = "comment3post1Reloaded body")
        val commentsToInsert = arrayListOf(comment3post1Reloaded, comment1post2, comment2post2)
        commentsDao.insertAll(commentsToInsert)

        // Check the table has 5 elements as comment3post1Reloaded already exists and will be replaced
        val commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(5))

        // Check the table contains the right comments
        assertThat(
            commentsList,
            contains(
                comment1post1,
                comment2post1,
                comment3post1Reloaded,
                comment1post2,
                comment2post2
            )
        )
    }

    @Test
    fun deleteExistingComment() {
        // Check the table has 3 elements
        var commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(3))

        // Then delete one comment
        commentsDao.delete(comment1post1)

        // Check the table has now 2 items
        commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(2))
        assertThat(commentsList, not(contains(comment1post1)))
    }

    @Test
    fun deleteNonExistingComment() {
        // Check the table has 3 elements
        var commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(3))

        // Then delete one comment
        commentsDao.delete(comment3post2)

        // Check the table has still 3 items
        commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(3))
    }

    @Test
    fun deleteAllComments() {
        // Check the table has 3 elements
        var commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(3))

        // Then delete everything
        commentsDao.deleteAll()

        // Check the table is now empty
        commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(0))
    }

    @Test
    fun getAllCommentsForExistingPost() {

        // Add post 2 comments to the base (post 1 comments already added)
        commentsDao.insertAll(testCommentsPost2)

        // Check the table has 6 elements
        val commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(6))

        val post1Comments = getValue(commentsDao.getCommentsForPost(1))

        // Check all the comments are in the list
        assertThat(post1Comments, contains(comment1post1, comment2post1, comment3post1))
        assertThat(post1Comments.size, equalTo(3))
    }

    @Test
    fun getAllCommentsForNonExistingPost() {

        val post42Comments = getValue(commentsDao.getCommentsForPost(42))

        // Check that nothing is returned
        assertThat(post42Comments.size, equalTo(0))
    }


    @Test
    fun deleteAllCommentsForExistingPost() {

        // Add post 2 comments to the base (post 1 comments already added)
        commentsDao.insertAll(testCommentsPost2)

        // Check the table has 6 elements
        var commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(6))

        commentsDao.deleteAllCommentsForPost(1)

        // Check the table has 3 elements
        commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(3))
        assertThat(commentsList, not(contains(comment1post1, comment2post1, comment3post1)))
    }


    @Test
    fun deleteAllCommentsForNonExistingPost() {

        // Add post 2 comments to the base (post 1 comments already added)
        commentsDao.insertAll(testCommentsPost2)

        // Check the table has 6 elements
        var commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(6))

        commentsDao.deleteAllCommentsForPost(42)

        // Check the table has still 6 elements
        commentsList = getValue(commentsDao.getAll())
        assertThat(commentsList.size, equalTo(6))
    }
}