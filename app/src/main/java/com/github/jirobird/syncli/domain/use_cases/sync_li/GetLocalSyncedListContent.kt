package com.github.jirobird.syncli.domain.use_cases.sync_li

import com.github.jirobird.syncli.data.repository.ISyncedListRepository
import com.github.jirobird.syncli.domain.model.synced_list.SyncedList

class GetLocalSyncedListContent(
    private val syncedListLocalRepository:ISyncedListRepository
){
    suspend operator fun invoke(id:String):SyncedList?{
        return syncedListLocalRepository.getSyncedListContent(id)
    }
}