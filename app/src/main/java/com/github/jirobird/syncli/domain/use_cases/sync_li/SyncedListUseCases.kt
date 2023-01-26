package com.github.jirobird.syncli.domain.use_cases.sync_li

data class SyncedListUseCases(
    val getLocalSyncedListCount: GetLocalSyncedListCount,
    val getLocalSyncedLists: GetLocalSyncedLists,
    val pushOrUpdateSyncedList: PushOrUpdateSyncedList,
    val getLocalSyncedListWithContent: GetLocalSyncedListWithContent,
    val getLocalSyncedListContent: GetLocalSyncedListContent,

    val getLocalSyncedListItemsCount: GetLocalSyncedListItemsCount
)