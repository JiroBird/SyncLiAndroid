package com.github.jirobird.syncli.screens.syncli.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.jirobird.syncli.domain.model.synced_list.SyncedList
import com.github.jirobird.syncli.domain.model.synced_list.SyncedListItem
import com.github.jirobird.syncli.domain.use_cases.sync_li.SyncedListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Math.random
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SyncLiViewModel @Inject constructor (
    private val syncedListUseCases: SyncedListUseCases
): ViewModel() {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private var getAllItemsJob: Job?=null

    private val _syncedListState = MutableStateFlow<List<SyncedList>?>(null)
    val syncedListState = _syncedListState.asStateFlow()

    init {
        fillFakeData()
        loadData()
    }

    private fun fillFakeData() {
        viewModelScope.launch {
            ioScope.launch {
                val count = syncedListUseCases.getLocalSyncedListCount()
                if (count == 0) {
                    (1..20).forEach {
                        val syncedList = SyncedList(UUID.randomUUID().toString(), Date().time, "SyncLi$it")
                        val randomSize = IntRange(0, (random() * 4).toInt())
                        if (randomSize.last > 0) {
                            val items = mutableListOf<SyncedListItem>()
                            randomSize.forEach { syncLiItemIterator->
                                items.add(SyncedListItem(UUID.randomUUID().toString(),Date().time, "SyncLiItem$syncLiItemIterator", syncedList.id))
                            }
                            syncedList.itemsCount = items.size
                            syncedList.itemList = items
                        }

                        syncedListUseCases.pushOrUpdateSyncedList(syncedList)
                    }
                }
            }
        }
    }

    private fun loadData() {
        getAllItemsJob?.cancel()
        getAllItemsJob = syncedListUseCases.getLocalSyncedListWithContent().onEach {
            _syncedListState.emit(null)
            _syncedListState.emit(it)
        }.launchIn(viewModelScope)
    }

    fun onUserUiEvent(event: SyncLiScreenUiEvent) {
        when(event) {
            SyncLiScreenUiEvent.SyncLiScreenUiEventRefreshList -> {
                loadData()
            }

            is SyncLiScreenUiEvent.SyncLiScreenUiEventItemClicked -> {
                viewModelScope.launch {
                    ioScope.launch{
                        val item = _syncedListState.value?.get(event.index)
                        item?.let { nnItem ->
                            val mutable = nnItem.itemList as MutableList<SyncedListItem>?
                            mutable?.let {
                                it.add(SyncedListItem(
                                    UUID.randomUUID().toString(),
                                    Date().time,
                                    "SyncLiItem${it.size}",
                                    nnItem.id
                                ))
                            }

                            syncedListUseCases.pushOrUpdateSyncedList(nnItem)

                            loadData()
                        }
                    }
                }
            }

            else -> {}
        }
    }
}