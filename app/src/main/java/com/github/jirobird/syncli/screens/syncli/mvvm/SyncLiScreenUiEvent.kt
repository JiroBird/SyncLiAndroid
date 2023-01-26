package com.github.jirobird.syncli.screens.syncli.mvvm

sealed class SyncLiScreenUiEvent {
    object SyncLiScreenUiEventRefreshList: SyncLiScreenUiEvent()
    object SyncLiScreenUiEventClearList:   SyncLiScreenUiEvent()
    class SyncLiScreenUiEventItemClicked(val index:Int):SyncLiScreenUiEvent()
}
