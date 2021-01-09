package com.demo.networkpagination.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.demo.networkpagination.dataModel.RowInfo

object CommunityComparator : DiffUtil.ItemCallback<RowInfo>() {
    override fun areContentsTheSame(oldItem: RowInfo, newItem: RowInfo): Boolean =
        oldItem.referenceCnt == newItem.referenceCnt

    override fun areItemsTheSame(oldItem: RowInfo, newItem: RowInfo): Boolean =
        oldItem.firstName == newItem.firstName
}