package com.michaellaguerre.symphony.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.michaellaguerre.symphony.R
import com.michaellaguerre.symphony.SymphonyApplication
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.ui.viewmodels.AuthorsViewModel

class AuthorsFragment : Fragment() {

    companion object {
        fun newInstance() = AuthorsFragment()
    }

    private lateinit var viewModel: AuthorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.authors_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthorsViewModel::class.java)
        // TODO: Use the ViewModel
        (requireActivity().applicationContext as SymphonyApplication).appComponent.inject(viewModel)


        viewModel.authors.observe(this, ::renderAuthorsList)
        viewModel.failure.observe(this, ::handleFailure)

        viewModel.loadAuthors()
    }

    private fun renderAuthorsList(authors: List<Author>?) {

        Log.e("RESULT", "Authors: " + authors?.size)

    }

    private fun handleFailure(failure: Failure?) {
        Log.e("RESULT", "Error: " + failure?.toString())
    }


}