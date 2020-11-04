package com.michaellaguerre.symphony.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.michaellaguerre.symphony.core.platform.BaseViewModel
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.entities.Post
import com.michaellaguerre.symphony.domain.interactors.GetAuthorDetail
import com.michaellaguerre.symphony.domain.interactors.GetAuthorPosts
import javax.inject.Inject

class AuthorDetailsViewModel(author: Author) : BaseViewModel() {

    @Inject
    lateinit var getAuthorDetail: GetAuthorDetail

    @Inject
    lateinit var getAuthorPosts: GetAuthorPosts

    var author: MutableLiveData<Author> = MutableLiveData()
    var posts: MutableLiveData<List<Post>> = MutableLiveData()


    //**********************************************************************************************
    // INITIALIZATION
    //**********************************************************************************************

    init {
        this.author = MutableLiveData(author)
    }


    //**********************************************************************************************
    // ACTIONS
    //**********************************************************************************************

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
        this.author.postValue(author)
    }

    private fun handleResultPosts(posts: List<Post>) {
        this.posts.postValue(posts)
    }


    //**********************************************************************************************
    // FACTORY
    //**********************************************************************************************

    class Factory(private val author: Author) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            try {
                return modelClass.getConstructor(Author::class.java).newInstance(author)
            } catch (e: Exception) {
                throw RuntimeException(
                    "Cannot create instance of$modelClass: it should have a (val author: Author) constructor",
                    e
                )
            }
        }
    }
}