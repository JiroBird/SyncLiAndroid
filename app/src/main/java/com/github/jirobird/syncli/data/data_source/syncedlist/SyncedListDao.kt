package com.github.jirobird.syncli.data.data_source.syncedlist

import androidx.room.*
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.SyncedListAndItem
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.SyncedListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SyncedListDao {
    @Query("SELECT * FROM syncedlistentity")
    fun getAllLists(): Flow<List<SyncedListEntity>>

    @Query("SELECT COUNT(id) FROM syncedlistentity")
    fun getListCount():Int

    @Query("SELECT * FROM syncedlistentity WHERE id =:id ORDER BY timestamp LIMIT 1")
    suspend fun getListById(id:String):SyncedListEntity?

    @Transaction
    @Query("SELECT * FROM syncedlistentity WHERE id = :id ORDER BY timestamp LIMIT 1")
    suspend fun getListWithItems(id: String): SyncedListAndItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSyncedList(syncedListEntity: SyncedListEntity)

    @Delete
    suspend fun deleteSyncedList(syncedListEntity: SyncedListEntity)
}