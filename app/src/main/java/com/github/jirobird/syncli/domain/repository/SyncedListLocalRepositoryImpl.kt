package com.github.jirobird.syncli.domain.repository

import com.github.jirobird.syncli.data.data_source.syncedlist.SyncedListDao
import com.github.jirobird.syncli.data.data_source.syncedlist.SyncedListItemDao
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.toSyncedList
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.toSyncedListEntity
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.toSyncedListItemEntity
import com.github.jirobird.syncli.data.repository.ISyncedListRepository
import com.github.jirobird.syncli.domain.model.synced_list.SyncedList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SyncedListLocalRepositoryImpl @Inject constructor(
    private val syncedListDao: SyncedListDao,
    private val syncedListItemDao: SyncedListItemDao
): ISyncedListRepository {
    override suspend fun getListCount(): Int {
        return syncedListDao.getListCount()
    }

    override fun getAllLists(): Flow<List<SyncedList>> {
        val dbFlow = syncedListDao.getAllLists()
        return dbFlow.map { it.map { syncedListEntity -> syncedListEntity.toSyncedList() } }
    }

    override suspend fun pushOrUpdateSyncedList(syncedList: SyncedList): Boolean {
        syncedListDao.insertSyncedList(syncedList.toSyncedListEntity())
        syncedList.itemList?.forEach {  syncedListItem ->
            syncedListItemDao.insertSyncedItem(syncedListItem.toSyncedListItemEntity(syncedList.id))
        }
        return true
    }
}