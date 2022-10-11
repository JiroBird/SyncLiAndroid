package com.github.jirobird.syncli.data.data_source.syncedlist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.SyncedListItemEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface SyncedListItemDao {
    @Query("SELECT * FROM syncedlistitementity")
    fun getAllItems(): Flow<List<SyncedListItemEntity>>

    @Query("SELECT * FROM syncedlistitementity WHERE id = :id ORDER BY timestamp LIMIT 1")
    suspend fun getItemById(id:String): SyncedListItemEntity?

    @Query("SELECT * FROM syncedlistitementity WHERE syncedListId = :listId")
    fun getItemsByListId(listId:UUID):  Flow<List<SyncedListItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSyncedItem(syncedListItemEntity: SyncedListItemEntity)

    @Delete
    suspend fun deleteSyncedItem(syncedListItemEntity: SyncedListItemEntity)
}