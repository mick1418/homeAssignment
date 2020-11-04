package com.michaellaguerre.symphony.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.michaellaguerre.symphony.core.platform.BaseViewModel
import com.michaellaguerre.symphony.domain.entities.Comment
import com.michaellaguerre.symphony.domain.entities.Post
import com.michaellaguerre.symphony.domain.interactors.GetPostComments
import com.michaellaguerre.symphony.domain.interactors.GetPostDetail
import javax.inject.Inject

class PostDetailsViewModel(post: Post) : BaseViewModel() {

    @Inject
    lateinit var getPostDetail: GetPostDetail

    @Inject
    lateinit var getPostComments: GetPostComments


    var post: MutableLiveData<Post> = MutableLiveData()
    var comments: MutableLiveData<List<Comment>> = MutableLiveData()


    //**********************************************************************************************
    // INITIALIZATION
    //**********************************************************************************************

    init {
        this.post = MutableLiveData(post)
    }

    //**********************************************************************************************
    // ACTIONS
    //**********************************************************************************************

    fun loadPost(postId: Int) = getPostDetail(GetPostDetail.Params(postId)) {
        it.fold(
            ::handleFailure,
            ::handleResultPost
        )
    }

    fun loadCommentsForPost(postId: Int) = getPostComments(GetPostComments.Params(postId)) {
        it.fold(
            ::handleFailure,
            ::handleResultComments
        )
    }

    private fun handleResultPost(post: Post) {
        this.post.postValue(post)
    }

    private fun handleResultComments(comments: List<Comment>) {
        this.comments.postValue(comments)
    }


    //**********************************************************************************************
    // FACTORY
    //**********************************************************************************************

    class Factory(private val post: Post) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            try {
                return modelClass.getConstructor(Post::class.java).newInstance(post)
            } catch (e: Exception) {
                throw RuntimeException(
                    "Cannot create instance of$modelClass: it should have a (val post: Post) constructor",
                    e
                )
            }
        }
    }
}