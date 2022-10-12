package com.github.jirobird.syncli.screens.syncli.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.jirobird.syncli.databinding.VhSyncliBinding
import com.github.jirobird.syncli.domain.model.synced_list.SyncedList

class SyncLiRecyclerViewAdapter: RecyclerView.Adapter<SyncLiViewHolder>() {
    private var list:List<SyncedList>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun pushItems(iList: List<SyncedList>?) {
        //TODO: добавить диффы и обновления вместо полной смены
        list = iList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SyncLiViewHolder {
        return SyncLiViewHolder(VhSyncliBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SyncLiViewHolder, position: Int) {
        holder.vhBinder.tvSyncliTitle.text = list?.get(position)?.title ?: holder.vhBinder.tvSyncliTitle.text
        holder.vhBinder.tvSyncliCount.text = list?.get(position)?.itemsCount.toString()
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}