package com.github.jirobird.syncli.data.repository

import com.github.jirobird.syncli.domain.model.synced_list.SyncedList
import kotlinx.coroutines.flow.Flow

interface ISyncedListRepository {
    suspend fun getListCount():Int
    fun getAllLists(): Flow<List<SyncedList>>
    suspend fun pushOrUpdateSyncedList(syncedList: SyncedList):Boolean
}