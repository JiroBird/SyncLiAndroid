package com.github.jirobird.syncli.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.jirobird.syncli.data.data_source.syncedlist.SyncedListDao
import com.github.jirobird.syncli.data.data_source.syncedlist.SyncedListItemDao
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.SyncedListEntity
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.SyncedListItemEntity

@Database(
    entities = [SyncedListEntity::class, SyncedListItemEntity::class],
    version = 1
)

abstract class SyncedListApplicationDatabase:RoomDatabase() {
    abstract val syncedListDao:SyncedListDao
    abstract val syncedListItemDao: SyncedListItemDao

    companion object {
        const val DATABASE_NAME = "synced_list_application_database"
    }
}
