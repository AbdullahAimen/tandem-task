package com.demo.networkpagination.repos.communityRepo

import androidx.paging.PagingSource
import com.demo.networkpagination.dataModel.RowInfo
import com.demo.networkpagination.network.ApiHelper
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

class CommunityPagingSource(private val mApiHelper: ApiHelper) : PagingSource<String, RowInfo>() {
    var pageIndex = 1
    override suspend fun load(params: LoadParams<String>): LoadResult<String, RowInfo> {
        return try {
            val data = mApiHelper.getCommunityPosts(params.key?.toInt() ?: pageIndex)
            if (data.response?.size == ApiHelper.PAGE_SIZE)
                pageIndex += 1
            LoadResult.Page(
                data = data.response!!,
                prevKey = null, //only load forward page
                nextKey = if (data.response?.size!! < ApiHelper.PAGE_SIZE) null else "$pageIndex"
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}