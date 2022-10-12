package com.github.jirobird.syncli.domain.use_cases.sync_li

import com.github.jirobird.syncli.data.repository.ISyncedListRepository
import com.github.jirobird.syncli.domain.model.synced_list.SyncedList

class PushOrUpdateSyncedList(
    private val syncedListLocalRepository: ISyncedListRepository
) {
    suspend operator fun invoke(syncedList: SyncedList): Boolean {
        return syncedListLocalRepository.pushOrUpdateSyncedList(syncedList)
    }
}