package com.github.jirobird.syncli.domain.use_cases.sync_li

import com.github.jirobird.syncli.data.repository.ISyncedListRepository

class GetLocalSyncedListItemsCount(
    private val syncedListLocalRepository:ISyncedListRepository
){
    suspend operator fun invoke():Int{
        return syncedListLocalRepository.getListCount()
    }
}