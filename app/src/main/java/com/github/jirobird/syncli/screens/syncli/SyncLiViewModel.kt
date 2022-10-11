package com.github.jirobird.syncli.screens.syncli

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.jirobird.syncli.domain.use_cases.sync_li.SyncedListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyncLiViewModel @Inject constructor (
    private val syncedListUseCases: SyncedListUseCases
): ViewModel() {
    private val ioScope = CoroutineScope(Dispatchers.IO)

    init {
        fillFakeData()
        loadData()
    }

    private fun fillFakeData() {
         viewModelScope.launch {
             ioScope.launch {
                 val count = syncedListUseCases.getLocalSyncedListCount()
                 if (count == 0) {
                     Log.d("TAG","$count")
                 }
             }
        }
    }

    private fun loadData() {

    }

    fun pushEvent() {

    }
}