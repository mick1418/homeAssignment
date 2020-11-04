package com.michaellaguerre.symphony.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.michaellaguerre.symphony.core.interactors.UseCase
import com.michaellaguerre.symphony.core.platform.BaseViewModel
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.interactors.GetAuthors
import javax.inject.Inject

class AuthorsViewModel : BaseViewModel() {

    @Inject
    lateinit var getAuthors: GetAuthors

    val authors: MutableLiveData<List<Author>> = MutableLiveData()


    //**********************************************************************************************
    // ACTIONS
    //**********************************************************************************************

    fun loadAuthors() = getAuthors(UseCase.None()) { it.fold(::handleFailure, ::handleResult) }

    private fun handleResult(authors: List<Author>) {
        this.authors.postValue(authors)
    }
}