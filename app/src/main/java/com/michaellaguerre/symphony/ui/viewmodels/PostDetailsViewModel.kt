package com.michaellaguerre.symphony.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.michaellaguerre.symphony.core.platform.BaseViewModel
import com.michaellaguerre.symphony.domain.entities.Comment
import com.michaellaguerre.symphony.domain.entities.Post
import com.michaellaguerre.symphony.domain.interactors.GetPostComments
import com.michaellaguerre.symphony.domain.interactors.GetPostDetail
import javax.inject.Inject

class PostDetailsViewModel : BaseViewModel() {

    @Inject
    lateinit var getPostDetail: GetPostDetail

    @Inject
    lateinit var getPostComments: GetPostComments

    private val _post: MutableLiveData<Post> = MutableLiveData()
    val post: LiveData<Post> = _post

    private val _comments: MutableLiveData<List<Comment>> = MutableLiveData()
    val comments: LiveData<List<Comment>> = _comments

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
        _post.value = post
    }

    private fun handleResultComments(comments: List<Comment>) {
        _comments.value = comments
    }
}