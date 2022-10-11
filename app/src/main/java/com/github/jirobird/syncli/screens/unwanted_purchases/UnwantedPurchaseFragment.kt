package com.github.jirobird.syncli.screens.unwanted_purchases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.github.jirobird.syncli.databinding.FragmentUnwantedPurchaseBinding
import com.github.jirobird.syncli.screens.common.ADiFragment

class UnwantedPurchaseFragment: ADiFragment() {
    lateinit var binding: FragmentUnwantedPurchaseBinding

    override fun getBaseLayoutViewFromBinding(inflater: LayoutInflater): View {
        if (!::binding.isInitialized) binding = FragmentUnwantedPurchaseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvFragmentName.text = this.javaClass.simpleName
    }
}