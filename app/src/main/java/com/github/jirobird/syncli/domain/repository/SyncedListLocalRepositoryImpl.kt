package com.github.jirobird.syncli.domain.repository

import com.github.jirobird.syncli.data.data_source.syncedlist.SyncedListDao
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.SyncedListEntity
import com.github.jirobird.syncli.data.repository.ISyncedListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SyncedListLocalRepositoryImpl @Inject constructor(
    private val syncedListDao: SyncedListDao
): ISyncedListRepository {
    override suspend fun getListCount(): Int {
        return syncedListDao.getListCount()
    }

    override suspend fun getAllLists(): Flow<List<SyncedListEntity>> {
        return syncedListDao.getAllLists()
    }
}