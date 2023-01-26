package com.github.jirobird.syncli.screens.syncli

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.jirobird.syncli.databinding.FragmentSyncliBinding
import com.github.jirobird.syncli.screens.common.ADiFragment
import com.github.jirobird.syncli.screens.syncli.mvvm.SyncLiScreenUiEvent
import com.github.jirobird.syncli.screens.syncli.mvvm.SyncLiViewModel
import com.github.jirobird.syncli.screens.syncli.view.SyncLiItemDecoration
import com.github.jirobird.syncli.screens.syncli.view.SyncLiRecyclerViewAdapter
import kotlinx.coroutines.launch

class SyncLiFragment: ADiFragment(), SyncLiRecyclerViewAdapter.ISyncLiRecyclerViewAdapterListener {
    lateinit var binding: FragmentSyncliBinding
    private val viewModel by viewModels<SyncLiViewModel>()

    override fun getBaseLayoutViewFromBinding(inflater: LayoutInflater): View {
        if (!::binding.isInitialized) binding = FragmentSyncliBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (binding.rvSyncedList.layoutManager == null) {
            binding.rvSyncedList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        }

        if (binding.rvSyncedList.adapter == null) {
            binding.rvSyncedList.adapter = SyncLiRecyclerViewAdapter().apply {
                syncLiRecyclerViewAdapterListener = this@SyncLiFragment
            }
        }

        if(binding.rvSyncedList.itemDecorationCount == 0) {
            binding.rvSyncedList.addItemDecoration(SyncLiItemDecoration())
        }

        binding.srlSyncliUpdate.setOnRefreshListener {
            viewModel.onUserUiEvent(SyncLiScreenUiEvent.SyncLiScreenUiEventRefreshList)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                viewModel.syncedListState.collect { syncli ->
                    binding.srlSyncliUpdate.isRefreshing = false
                    binding.rvSyncedList.adapter?.let { adapter ->
                        (adapter as SyncLiRecyclerViewAdapter).pushItems(syncli)
                    }
                }
            }
        }
    }

    override fun onItemClicked(itemClick: SyncLiScreenUiEvent) {
        viewModel.onUserUiEvent(itemClick)
    }
}