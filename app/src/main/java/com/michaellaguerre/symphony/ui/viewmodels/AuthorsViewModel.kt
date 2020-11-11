package com.michaellaguerre.symphony.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.interactors.GetAuthors
import javax.inject.Inject

class AuthorsViewModel : ViewModel() {

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