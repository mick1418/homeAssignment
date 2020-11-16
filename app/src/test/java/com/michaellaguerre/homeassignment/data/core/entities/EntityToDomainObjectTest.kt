package com.michaellaguerre.homeassignment.data.core.entities

import com.michaellaguerre.homeassignment.data.network.author145
import com.michaellaguerre.homeassignment.data.network.comment75001
import com.michaellaguerre.homeassignment.data.network.post1
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class EntityToDomainObjectTest {

    //**********************************************************************************************
    // TESTS
    //**********************************************************************************************

    @Test
    fun convertAuthorEntityToAuthor() {
        val authorEntity = author145
        val author = authorEntity.toAuthor()

        assertThat(author.id, equalTo(authorEntity.id))
        assertThat(author.name, equalTo(authorEntity.name))
        assertThat(author.userName, equalTo(authorEntity.userName))
        assertThat(author.email, equalTo(authorEntity.email))
        assertThat(author.avatarUrl, equalTo(authorEntity.avatarUrl))
        assertThat(author.address.latitude, equalTo(authorEntity.address.latitude.toDouble()))
        assertThat(author.address.longitude, equalTo(authorEntity.address.longitude.toDouble()))
    }

    @Test
    fun convertPostEntityToPost() {
        val postEntity = post1
        val post = postEntity.toPost()

        assertThat(post.id, equalTo(postEntity.id))
        assertThat(post.date, equalTo(postEntity.date))
        assertThat(post.imageUrl, equalTo(postEntity.imageUrl))
        assertThat(post.title, equalTo(postEntity.title))
        assertThat(post.body, equalTo(postEntity.body))
        assertThat(post.authorId, equalTo(postEntity.authorId))
    }

    @Test
    fun convertCommentEntityToComment() {
        val commentEntity = comment75001
        val comment = commentEntity.toComment()

        assertThat(comment.id, equalTo(commentEntity.id))
        assertThat(comment.date, equalTo(commentEntity.date))
        assertThat(comment.avatarUrl, equalTo(commentEntity.avatarUrl))
        assertThat(comment.email, equalTo(commentEntity.email))
        assertThat(comment.body, equalTo(commentEntity.body))
        assertThat(comment.postId, equalTo(commentEntity.postId))
        assertThat(comment.userName, equalTo(commentEntity.userName))
    }
}
