package com.github.jirobird.syncli.screens.syncli

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.github.jirobird.syncli.databinding.FragmentSyncliBinding
import com.github.jirobird.syncli.screens.common.ADiFragment

class SyncLiFragment: ADiFragment() {
    lateinit var binding: FragmentSyncliBinding
    val viewModel by viewModels<SyncLiViewModel>()

    override fun getBaseLayoutViewFromBinding(inflater: LayoutInflater): View {
        if (!::binding.isInitialized) binding = FragmentSyncliBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.pushEvent()
    }
}