package com.michaellaguerre.homeassignment.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import com.michaellaguerre.homeassignment.domain.entities.Author
import com.michaellaguerre.homeassignment.domain.entities.Post
import com.michaellaguerre.homeassignment.domain.interactors.ContactByMail
import com.michaellaguerre.homeassignment.domain.interactors.GetAuthorPosts
import javax.inject.Inject

/**
 * ViewModel class used to handle everything related to the author details screen.
 *
 * @param author an [Author]
 */
class AuthorDetailsViewModel(author: Author) : ViewModel() {

    @Inject
    lateinit var getAuthorPosts: GetAuthorPosts

    @Inject
    lateinit var contactByMail: ContactByMail

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


    /**
     * Retrieve the list of [Post] for a given [Author].
     *
     * @param authorId the author's ID
     * @return a LiveData<PagingData<Post>> containing the pages with the author's posts
     */
    fun loadPostsForAuthor(authorId: Int) {
        this.posts = getAuthorPosts.invoke(GetAuthorPosts.Params(authorId))
    }


    /**
     * Open an email client and start a new mail to the given email address
     *
     * @param activityContext the Activity [Context]
     * @param emailAddress the email address
     */
    fun contactByMail(activityContext: Context, emailAddress: String) {
        contactByMail.invoke(ContactByMail.Params(activityContext, emailAddress))
    }


    //**********************************************************************************************
    // FACTORY
    //**********************************************************************************************


    /**
     * Factory used to create and initialize an [AuthorDetailsViewModel] with a given [Author]
     *
     * @param author the [Author]
     */
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