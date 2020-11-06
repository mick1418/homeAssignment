package com.michaellaguerre.symphony.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.michaellaguerre.symphony.core.platform.BaseViewModel
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.interactors.GetAuthors
import javax.inject.Inject

class AuthorsViewModel : BaseViewModel() {

    @Inject
    lateinit var getAuthors: GetAuthors

    lateinit var authors: LiveData<PagingData<Author>>


    //**********************************************************************************************
    // ACTIONS
    //**********************************************************************************************

    fun loadAuthors() {
        this.authors = getAuthors.invoke()
    }
}