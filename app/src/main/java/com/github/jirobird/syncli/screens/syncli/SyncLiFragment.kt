package com.github.jirobird.syncli.screens.syncli

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.jirobird.syncli.databinding.FragmentSyncliBinding
import com.github.jirobird.syncli.screens.common.ADiFragment
import com.github.jirobird.syncli.screens.syncli.view.SyncLiItemDecoration
import com.github.jirobird.syncli.screens.syncli.view.SyncLiRecyclerViewAdapter
import kotlinx.coroutines.launch

class SyncLiFragment: ADiFragment() {
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
            binding.rvSyncedList.adapter = SyncLiRecyclerViewAdapter()
        }

        if(binding.rvSyncedList.itemDecorationCount == 0) {
            binding.rvSyncedList.addItemDecoration(SyncLiItemDecoration())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                viewModel.syncedListState.collect { syncli ->
                    binding.rvSyncedList.adapter?.let { adapter ->
                        (adapter as SyncLiRecyclerViewAdapter).pushItems(syncli)
                    }
                }
            }
        }
    }
}