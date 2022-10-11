package com.github.jirobird.syncli.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.jirobird.syncli.R
import com.github.jirobird.syncli.databinding.ActivityMainStateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainStateActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainStateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainStateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fl_nav_controller)
        binding.bnv.setupWithNavController(navController)
    }
}