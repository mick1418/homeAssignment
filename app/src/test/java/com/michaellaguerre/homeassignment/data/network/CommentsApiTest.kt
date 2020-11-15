package com.michaellaguerre.homeassignment.data.network

import com.michaellaguerre.homeassignment.data.network.api.CommentsApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CommentsApiTest : BaseApiTest() {

    private lateinit var commentsApi: CommentsApi


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    @Before
    fun createApi() {
        commentsApi = retrofit.create(CommentsApi::class.java)
    }


    //**********************************************************************************************
    // TESTS
    //**********************************************************************************************

    @Test
    fun getCommentsForPostPageWithData() {
        enqueueResponse(mockWebServer, "post1_comments_page1_limit4.json")

        runBlocking {

            // Test response
            val page1 = commentsApi.getCommentsFromPost(1, 1, 4)
            Assert.assertThat(page1.size, equalTo(4))

            val comment = page1.first()
            Assert.assertThat(comment, equalTo(comment75001))

            // Test request
            val request = mockWebServer.takeRequest()
            Assert.assertThat(
                request.path,
                equalTo("/posts/1/comments?_sort=date&_order=asc&_page=1&_limit=4")
            )
        }
    }

    @Test
    fun getPostsForAuthorPageEmpty() {
        enqueueResponse(mockWebServer, "empty_array.json")

        runBlocking {

            // Test response
            val data = commentsApi.getCommentsFromPost(2000, 1, 4)
            Assert.assertThat(data.size, equalTo(0))

            // Test request
            val request = mockWebServer.takeRequest()
            Assert.assertThat(
                request.path,
                equalTo("/posts/2000/comments?_sort=date&_order=asc&_page=1&_limit=4")
            )
        }
    }
}
