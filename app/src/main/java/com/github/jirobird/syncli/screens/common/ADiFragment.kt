package com.github.jirobird.syncli.screens.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class ADiFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getBaseLayoutViewFromBinding(inflater) ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun getBaseLayoutViewFromBinding(inflater: LayoutInflater):View?
}