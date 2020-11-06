package com.michaellaguerre.symphony.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.michaellaguerre.symphony.R
import com.michaellaguerre.symphony.databinding.NetworkStateViewBinding

class LoadingStateAdapter(
    private val adapter: PagingDataAdapter<*, *>
) : LoadStateAdapter<LoadingStateAdapter.NetworkStateItemViewHolder>() {
    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder(parent) { adapter.retry() }
    }


    /**
     * A View Holder that can display a loading or have click action.
     * It is used to show the network state of paging.
     */
    class NetworkStateItemViewHolder(
        parent: ViewGroup,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.network_state_view, parent, false)
    ) {
        private val binding = NetworkStateViewBinding.bind(itemView)
        private val progressBar = binding.progressBar
        private val errorMessage = binding.errorMessage
        private val retry = binding.retryButton
            .also {
                it.setOnClickListener { retryCallback() }
            }

        fun bindTo(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
            retry.isVisible = loadState is LoadState.Error
            errorMessage.isVisible =
                !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMessage.text = (loadState as? LoadState.Error)?.error?.message
        }
    }
}