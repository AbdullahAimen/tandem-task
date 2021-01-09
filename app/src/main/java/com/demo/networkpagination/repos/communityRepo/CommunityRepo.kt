package com.demo.networkpagination.repos.communityRepo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.demo.networkpagination.network.ApiHelper

class CommunityRepo(private val mApiHelper: ApiHelper) {
    fun getCommunityList() = Pager(
        PagingConfig(ApiHelper.PAGE_SIZE)
    ) {
        CommunityPagingSource(mApiHelper)
    }.flow
}