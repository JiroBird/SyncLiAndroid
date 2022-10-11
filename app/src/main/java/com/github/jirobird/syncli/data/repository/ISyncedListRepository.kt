package com.github.jirobird.syncli.data.repository

import com.github.jirobird.syncli.data.data_source.syncedlist.entity.SyncedListEntity
import kotlinx.coroutines.flow.Flow

interface ISyncedListRepository {
    suspend fun getListCount():Int
    suspend fun getAllLists(): Flow<List<SyncedListEntity>>
}