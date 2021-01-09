package com.demo.networkpagination.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.demo.networkpagination.repos.communityRepo.CommunityRepo

class CommunityViewModel(
    private val mCommunityRepo: CommunityRepo,
) : ViewModel() {

    val communityFlow = mCommunityRepo.getCommunityList().cachedIn(viewModelScope)
}