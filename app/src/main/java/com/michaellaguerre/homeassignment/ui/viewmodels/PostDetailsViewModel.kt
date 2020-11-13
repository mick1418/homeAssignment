package com.michaellaguerre.homeassignment.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import com.michaellaguerre.homeassignment.domain.entities.Author
import com.michaellaguerre.homeassignment.domain.entities.Comment
import com.michaellaguerre.homeassignment.domain.entities.Post
import com.michaellaguerre.homeassignment.domain.interactors.GetPostComments
import javax.inject.Inject

/**
 * ViewModel class used to handle everything related to the post details screen.
 *
 * @param author an [Author]
 * @param post an [Post]
 */
class PostDetailsViewModel(author: Author, post: Post) : ViewModel() {

    @Inject
    lateinit var getPostComments: GetPostComments


    var author: MutableLiveData<Author> = MutableLiveData()
    var post: MutableLiveData<Post> = MutableLiveData()
    lateinit var comments: LiveData<PagingData<Comment>>


    //**********************************************************************************************
    // INITIALIZATION
    //**********************************************************************************************

    init {
        this.author = MutableLiveData(author)
        this.post = MutableLiveData(post)
    }

    //**********************************************************************************************
    // ACTIONS
    //**********************************************************************************************


    /**
     * Retrieve the list of [Comment] for a given [Post].
     *
     * @param postId the post's ID
     * @return a LiveData<PagingData<Comment>> containing the pages with the post's comment
     */
    fun loadCommentsForPost(postId: Int) {
        this.comments = getPostComments.invoke(GetPostComments.Params(postId))
    }


    //**********************************************************************************************
    // FACTORY
    //**********************************************************************************************


    /**
     * Factory used to create and initialize an [PostDetailsViewModel] with a given [Post] and [Author]
     *
     * @param author the [Author]
     * @param post the [Post]
     */
    class Factory(private val author: Author, private val post: Post) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            try {
                return modelClass.getConstructor(Author::class.java, Post::class.java)
                    .newInstance(author, post)
            } catch (e: Exception) {
                throw RuntimeException(
                    "Cannot create instance of$modelClass: it should have a (val author: Author, val post: Post) constructor",
                    e
                )
            }
        }
    }
}