package com.github.jirobird.syncli.screens.syncli

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.jirobird.syncli.databinding.FragmentSyncliBinding
import com.github.jirobird.syncli.screens.common.ADiFragment

class SyncLiFragment: ADiFragment() {
    lateinit var binding: FragmentSyncliBinding
    private val viewModel by viewModels<SyncLiViewModel>()

    override fun getBaseLayoutViewFromBinding(inflater: LayoutInflater): View {
        if (!::binding.isInitialized) binding = FragmentSyncliBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.syncedListState.collect {
                Log.d("","")
            }
        }
    }
}