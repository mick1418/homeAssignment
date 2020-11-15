package com.michaellaguerre.homeassignment.data.network

import com.michaellaguerre.homeassignment.data.network.api.AuthorsApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AuthorsApiTest : BaseApiTest() {

    private lateinit var authorsApi: AuthorsApi


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    @Before
    fun createApi() {
        authorsApi = retrofit.create(AuthorsApi::class.java)
    }


    //**********************************************************************************************
    // TESTS
    //**********************************************************************************************

    @Test
    fun getAuthorsPageWithData() {
        enqueueResponse(mockWebServer, "authors_page1_limit4.json")

        runBlocking {

            // Test response
            val page1 = authorsApi.getAuthors(1, 4)
            Assert.assertThat(page1.size, equalTo(4))

            val author = page1.first()
            Assert.assertThat(author, equalTo(author145))

            // Test request
            val request = mockWebServer.takeRequest()
            Assert.assertThat(
                request.path,
                equalTo("/authors?_sort=name&_order=asc&_page=1&_limit=4")
            )
        }
    }

    @Test
    fun getAuthorsPageEmpty() {
        enqueueResponse(mockWebServer, "empty_array.json")

        runBlocking {

            // Test response
            val data = authorsApi.getAuthors(100, 4)
            Assert.assertThat(data.size, equalTo(0))

            // Test request
            val request = mockWebServer.takeRequest()
            Assert.assertThat(
                request.path,
                equalTo("/authors?_sort=name&_order=asc&_page=100&_limit=4")
            )
        }
    }

    @Test
    fun getExistingAuthor() {
        enqueueResponse(mockWebServer, "author145_details.json")

        runBlocking {

            // Test response
            val data = authorsApi.getAuthor(145)
            Assert.assertThat(data, notNullValue())
            Assert.assertThat(data, equalTo(author145))

            // Test request
            val request = mockWebServer.takeRequest()
            Assert.assertThat(
                request.path,
                equalTo("/authors/145")
            )
        }
    }

    @Test
    fun getNonExistingAuthor() {
        enqueueResponse(mockWebServer, "empty_object.json")

        runBlocking {

            // Test response
            val data = authorsApi.getAuthor(2000)
            Assert.assertThat(data.id, equalTo(0))
            Assert.assertThat(data.name, nullValue())
            Assert.assertThat(data.userName, nullValue())
            Assert.assertThat(data.email, nullValue())
            Assert.assertThat(data.avatarUrl, nullValue())
            Assert.assertThat(data.address, nullValue())

            // Test request
            val request = mockWebServer.takeRequest()
            Assert.assertThat(
                request.path,
                equalTo("/authors/2000")
            )
        }
    }
}
