package com.github.jirobird.syncli.domain.model.synced_list

data class SyncedList(val id:String,
                      val timestamp: Long,
                      val title: String,
                      var itemsCount: Int = 0,
                      var itemList: List<SyncedListItem>? = null)