package com.github.jirobird.syncli.domain.model.synced_list

data class SyncedList(val id:String, val timestamp: Long, val title: String,
                      val itemList: List<SyncedListItem>? = null)