package com.github.jirobird.syncli.screens.sharedli

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.github.jirobird.syncli.databinding.FragmentSharedliBinding
import com.github.jirobird.syncli.screens.common.ADiFragment

class SharedLiFragment: ADiFragment() {
    lateinit var binding: FragmentSharedliBinding

    override fun getBaseLayoutViewFromBinding(inflater: LayoutInflater): View {
        if (!::binding.isInitialized) binding = FragmentSharedliBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvFragmentName.text = this.javaClass.simpleName
    }
}