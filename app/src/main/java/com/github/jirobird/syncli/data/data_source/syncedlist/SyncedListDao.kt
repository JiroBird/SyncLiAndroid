package com.github.jirobird.syncli.data.data_source.syncedlist

import androidx.room.*
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.SyncedListAndItem
import com.github.jirobird.syncli.data.data_source.syncedlist.entity.SyncedListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SyncedListDao {
    @Query("SELECT * FROM SyncedListEntity")
    fun getAllLists(): Flow<List<SyncedListEntity>>

    @Query("SELECT COUNT(id) FROM SyncedListEntity")
    fun getListCount():Int

    @Query("SELECT * FROM SyncedListEntity WHERE id =:id ORDER BY timestamp LIMIT 1")
    suspend fun getListById(id:String):SyncedListEntity?

    @Transaction
    @Query("SELECT * FROM SyncedListEntity WHERE id = :id ORDER BY timestamp LIMIT 1")
    fun getAllListWithItems(id: String): SyncedListAndItem?

    @Transaction
    @Query("SELECT * FROM SyncedListEntity")
    fun getAllListWithItems():Flow<List<SyncedListAndItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSyncedList(syncedListEntity: SyncedListEntity)

    @Delete
    suspend fun deleteSyncedList(syncedListEntity: SyncedListEntity)

    @Query("DELETE FROM SyncedListEntity")
    suspend fun dropTable()
}