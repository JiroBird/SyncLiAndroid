package com.github.jirobird.syncli.data.data_source.syncedlist.entity

import com.github.jirobird.syncli.domain.model.synced_list.SyncedList
import com.github.jirobird.syncli.domain.model.synced_list.SyncedListItem


fun SyncedListEntity.toSyncedList():SyncedList {
    return SyncedList(id, timestamp, title)
}

fun SyncedListItemEntity.toSyncedListItem():SyncedListItem {
    return SyncedListItem(this.id, this.timestamp, this.title, this.syncedListId)
}

fun SyncedListAndItem.toSyncedList():SyncedList {
    return SyncedList(syncedListEntity.id, syncedListEntity.timestamp, syncedListEntity.title, syncedListItems.map {
            syncedListItemEntity -> syncedListItemEntity.toSyncedListItem()
    })
}

fun SyncedList.toSyncedListEntity():SyncedListEntity {
    return SyncedListEntity(id, timestamp, title)
}

fun SyncedListItem.toSyncedListItemEntity(iSyncedListItemId:String? = null):SyncedListItemEntity {
    val parentId = if (iSyncedListItemId == null || iSyncedListItemId == syncedListId) {
        syncedListId
    } else {
        iSyncedListItemId
    }

    return SyncedListItemEntity(id, timestamp, title, parentId)
}