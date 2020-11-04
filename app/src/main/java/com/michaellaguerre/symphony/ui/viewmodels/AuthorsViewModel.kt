package com.michaellaguerre.symphony.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.michaellaguerre.symphony.core.interactors.UseCase
import com.michaellaguerre.symphony.core.platform.BaseViewModel
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.interactors.GetAuthors
import javax.inject.Inject

class AuthorsViewModel : BaseViewModel() {

    @Inject
    lateinit var getAuthors: GetAuthors

    private val _authors: MutableLiveData<List<Author>> = MutableLiveData()
    val authors: LiveData<List<Author>> = _authors

    fun loadAuthors() = getAuthors(UseCase.None()) { it.fold(::handleFailure, ::handleResult) }

    private fun handleResult(authors: List<Author>) {
        _authors.value = authors
        //TODO add a viewEntity ?
    }
}