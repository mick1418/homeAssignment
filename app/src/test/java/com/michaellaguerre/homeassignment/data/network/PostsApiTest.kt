package com.michaellaguerre.homeassignment.data.network

import com.michaellaguerre.homeassignment.data.network.api.PostsApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PostsApiTest : BaseApiTest() {

    private lateinit var postsApi: PostsApi


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    @Before
    fun createApi() {
        postsApi = retrofit.create(PostsApi::class.java)
    }


    //**********************************************************************************************
    // TESTS
    //**********************************************************************************************

    @Test
    fun getPostsForAuthorPageWithData() {
        enqueueResponse(mockWebServer, "author1_posts_page1_limit4.json")

        runBlocking {

            // Test response
            val page1 = postsApi.getPostsFromAuthor(1, 1, 4)
            Assert.assertThat(page1.size, equalTo(4))

            val post = page1.first()
            Assert.assertThat(post, equalTo(post1))

            // Test request
            val request = mockWebServer.takeRequest()
            Assert.assertThat(
                request.path,
                equalTo("/authors/1/posts?_sort=date&_order=desc&_page=1&_limit=4")
            )
        }
    }

    @Test
    fun getPostsForAuthorPageEmpty() {
        enqueueResponse(mockWebServer, "empty_array.json")

        runBlocking {

            // Test response
            val data = postsApi.getPostsFromAuthor(2000, 1, 4)
            Assert.assertThat(data.size, equalTo(0))

            // Test request
            val request = mockWebServer.takeRequest()
            Assert.assertThat(
                request.path,
                equalTo("/authors/2000/posts?_sort=date&_order=desc&_page=1&_limit=4")
            )
        }
    }

    @Test
    fun getExistingPost() {
        enqueueResponse(mockWebServer, "post1_details.json")

        runBlocking {

            // Test response
            val data = postsApi.getPost(1)
            Assert.assertThat(data, notNullValue())
            Assert.assertThat(data, equalTo(post1))

            // Test request
            val request = mockWebServer.takeRequest()
            Assert.assertThat(
                request.path,
                equalTo("/posts/1")
            )
        }
    }

    @Test
    fun getNonExistingPost() {
        enqueueResponse(mockWebServer, "empty_object.json")

        runBlocking {

            // Test response
            val data = postsApi.getPost(2000)
            Assert.assertThat(data.id, equalTo(0))
            Assert.assertThat(data.date, nullValue())
            Assert.assertThat(data.title, nullValue())
            Assert.assertThat(data.body, nullValue())
            Assert.assertThat(data.imageUrl, nullValue())
            Assert.assertThat(data.authorId, equalTo(0))

            // Test request
            val request = mockWebServer.takeRequest()
            Assert.assertThat(
                request.path,
                equalTo("/posts/2000")
            )
        }
    }
}
