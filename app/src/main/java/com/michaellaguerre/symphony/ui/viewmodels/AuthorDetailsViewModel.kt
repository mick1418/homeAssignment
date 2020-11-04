package com.michaellaguerre.symphony.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.michaellaguerre.symphony.core.platform.BaseViewModel
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.entities.Post
import com.michaellaguerre.symphony.domain.interactors.GetAuthorDetail
import com.michaellaguerre.symphony.domain.interactors.GetAuthorPosts
import javax.inject.Inject

class AuthorDetailsViewModel : BaseViewModel() {

    @Inject
    lateinit var getAuthorDetail: GetAuthorDetail
    @Inject
    lateinit var getAuthorPosts: GetAuthorPosts

    private val _author: MutableLiveData<Author> = MutableLiveData()
    val author: LiveData<Author> = _author

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>> = _posts

    fun loadAuthor(authorId: Int) = getAuthorDetail(GetAuthorDetail.Params(authorId)) {
        it.fold(
            ::handleFailure,
            ::handleResultAuthor
        )
    }

    fun loadPostsForAuthor(authorId: Int) = getAuthorPosts(GetAuthorPosts.Params(authorId)) {
        it.fold(
            ::handleFailure,
            ::handleResultPosts
        )
    }

    private fun handleResultAuthor(author: Author) {
        _author.value = author
    }

    private fun handleResultPosts(posts: List<Post>) {
        _posts.value = posts
    }
}