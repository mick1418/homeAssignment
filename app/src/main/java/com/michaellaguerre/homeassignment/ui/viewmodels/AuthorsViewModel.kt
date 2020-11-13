package com.michaellaguerre.homeassignment.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.michaellaguerre.homeassignment.domain.entities.Author
import com.michaellaguerre.homeassignment.domain.interactors.GetAuthors
import javax.inject.Inject

/**
 * ViewModel class used to handle everything related to the authors list screen.
 */
class AuthorsViewModel : ViewModel() {

    @Inject
    lateinit var getAuthors: GetAuthors

    lateinit var authors: LiveData<PagingData<Author>>


    //**********************************************************************************************
    // ACTIONS
    //**********************************************************************************************


    /**
     * Retrieve the list of [Author].
     *
     * @return a LiveData<PagingData<Author>> containing the pages with the authors
     */
    fun loadAuthors() {
        this.authors = getAuthors.invoke()
    }
}