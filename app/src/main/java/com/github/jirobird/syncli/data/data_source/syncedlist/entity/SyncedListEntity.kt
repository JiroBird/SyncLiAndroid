package com.github.jirobird.syncli.data.data_source.syncedlist.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class SyncedListEntity(
    @PrimaryKey
    val id:String = UUID.randomUUID().toString(),
    val timestamp: Long,
    val title: String,
    val itemCount: Int = 0
)