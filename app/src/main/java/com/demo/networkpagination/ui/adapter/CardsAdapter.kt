package com.demo.networkpagination.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.networkpagination.R
import com.demo.networkpagination.dataModel.RowInfo
import com.demo.networkpagination.databinding.ItemCommunityCardBinding

class CardsAdapter : PagingDataAdapter<RowInfo, CardsAdapter.CardViewHolder>(CommunityComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_community_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CardViewHolder(private val mRootView: View) : RecyclerView.ViewHolder(mRootView) {
        private val binding = ItemCommunityCardBinding.bind(mRootView)
        private var mRowInfo: RowInfo? = null

        fun bind(item: RowInfo?) {
            this.mRowInfo = item
            binding.firstName.text = mRowInfo?.firstName
            binding.bio.text = mRowInfo?.topic
            binding.nativeLanguagesTv.text = mRowInfo?.natives.toString()
            binding.learnsLanguagesTv.text = mRowInfo?.learns.toString()
            if (mRowInfo?.referenceCnt == 0) {
                binding.referenceNew.visibility = View.VISIBLE
                binding.referenceCnt.visibility = View.GONE
            } else {
                binding.referenceCnt.visibility = View.VISIBLE
                binding.referenceNew.visibility = View.GONE
                binding.referenceCnt.text = "${mRowInfo?.referenceCnt}"
            }
            Glide.with(mRootView).load(mRowInfo?.avatar).into(binding.thumbnail)        }
    }
}