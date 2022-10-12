package com.github.jirobird.syncli.data.repository

import com.github.jirobird.syncli.domain.model.synced_list.SyncedList
import com.github.jirobird.syncli.domain.model.synced_list.SyncedListItem
import kotlinx.coroutines.flow.Flow

interface ISyncedListRepository {
    suspend fun getListCount():Int
    fun getAllLists(): Flow<List<SyncedList>>
    fun getAllListsWithContent(): Flow<List<SyncedList>>
    suspend fun getSyncedListContent(id:String):SyncedList?
    suspend fun pushOrUpdateSyncedList(syncedList: SyncedList):Boolean


    suspend fun getItemsCount():Int
    fun getAllItems():Flow<List<SyncedListItem>>
}