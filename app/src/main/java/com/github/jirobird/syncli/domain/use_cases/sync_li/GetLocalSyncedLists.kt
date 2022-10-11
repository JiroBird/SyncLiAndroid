package com.github.jirobird.syncli.domain.use_cases.sync_li

import com.github.jirobird.syncli.data.data_source.syncedlist.entity.SyncedListEntity
import com.github.jirobird.syncli.data.repository.ISyncedListRepository
import kotlinx.coroutines.flow.Flow

class GetLocalSyncedLists(
    private val syncedListLocalRepository:ISyncedListRepository
){
    suspend operator fun invoke():Flow<List<SyncedListEntity>> {
        return syncedListLocalRepository.getAllLists()
    }
}