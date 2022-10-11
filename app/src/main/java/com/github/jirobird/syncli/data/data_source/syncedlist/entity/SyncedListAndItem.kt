package com.github.jirobird.syncli.data.data_source.syncedlist.entity

import androidx.room.Embedded
import androidx.room.Relation

data class SyncedListAndItem(
    @Embedded val syncedListEntity: SyncedListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "syncedListId"
    )
    val syncedListItems: List<SyncedListItemEntity>
)
