package com.demo.networkpagination.network

import com.demo.networkpagination.dataModel.CommunityResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiHelper {
    @GET("/api/community_{page_no}.json")
    suspend fun getCommunityPosts(
        @Path("page_no") pageIndex: Int? = 1
    ): CommunityResponse

    companion object {
        const val BASE_URL = "https://tandem2019.web.app/"
        const val PAGE_SIZE = 20
    }
}