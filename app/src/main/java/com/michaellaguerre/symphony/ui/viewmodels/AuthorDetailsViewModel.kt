package com.michaellaguerre.symphony.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import com.michaellaguerre.symphony.core.platform.BaseViewModel
import com.michaellaguerre.symphony.data.Resource
import com.michaellaguerre.symphony.data.entities.PostEntity
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.entities.Post
import com.michaellaguerre.symphony.domain.interactors.GetAuthorPosts
import javax.inject.Inject

class AuthorDetailsViewModel(author: Author) : BaseViewModel() {

    @Inject
    lateinit var getAuthorPosts: GetAuthorPosts

    var author: MutableLiveData<Author> = MutableLiveData()
    lateinit var posts: LiveData<PagingData<Post>>


    //**********************************************************************************************
    // INITIALIZATION
    //**********************************************************************************************

    init {
        this.author = MutableLiveData(author)
    }


    //**********************************************************************************************
    // ACTIONS
    //**********************************************************************************************


    fun loadPostsForAuthor(authorId: Int) {
        this.posts = getAuthorPosts.invoke(GetAuthorPosts.Params(authorId))
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