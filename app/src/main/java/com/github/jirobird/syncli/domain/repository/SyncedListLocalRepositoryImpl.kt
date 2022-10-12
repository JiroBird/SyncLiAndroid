package com.github.jirobird.syncli.domain.repository

import com.github.jirobird.syncli.data.data_source.syncedlist.SyncedListDao
import com.github.jirobird.syncli.data.data_source.syncedlist.SyncedListItemDao
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.toSyncedList
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.toSyncedListEntity
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.toSyncedListItem
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.toSyncedListItemEntity
import com.github.jirobird.syncli.data.repository.ISyncedListRepository
import com.github.jirobird.syncli.domain.model.synced_list.SyncedList
import com.github.jirobird.syncli.domain.model.synced_list.SyncedListItem
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

    override fun getAllListsWithContent(): Flow<List<SyncedList>> {
        val dbFlow = syncedListDao.getAllListWithItems()
        return dbFlow.map { it.map { syncedListAndItem -> syncedListAndItem.toSyncedList() } }
    }

    override suspend fun getSyncedListContent(id: String): SyncedList? {
        return syncedListDao.getAllListWithItems(id)?.toSyncedList()
    }

    override suspend fun pushOrUpdateSyncedList(syncedList: SyncedList): Boolean {
        syncedListDao.insertSyncedList(syncedList.toSyncedListEntity())

        syncedList.itemList?.forEach {  syncedListItem ->
            syncedListItemDao.insertSyncedItem(syncedListItem.toSyncedListItemEntity(syncedList.id))
        }
        return true
    }

    override suspend fun getItemsCount(): Int {
        return syncedListItemDao.getItemsCount()
    }

    override fun getAllItems():Flow<List<SyncedListItem>> {
        val dbFlow = syncedListItemDao.getAllItems()
        return dbFlow.map { it.map { syncedListItemEntity -> syncedListItemEntity.toSyncedListItem()} }
    }
}