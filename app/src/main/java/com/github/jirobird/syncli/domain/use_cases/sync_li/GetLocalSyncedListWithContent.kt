package com.github.jirobird.syncli.domain.use_cases.sync_li

import com.github.jirobird.syncli.data.repository.ISyncedListRepository
import com.github.jirobird.syncli.domain.model.synced_list.SyncedList
import kotlinx.coroutines.flow.Flow

class GetLocalSyncedListWithContent(
    private val syncedListLocalRepository:ISyncedListRepository
){
    operator fun invoke():Flow<List<SyncedList>> {
        return syncedListLocalRepository.getAllListsWithContent()
    }
}