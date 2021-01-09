package com.demo.networkpagination.dataModel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommunityResponse(
    @SerializedName("response")
    var response: List<RowInfo>? = arrayListOf(),
    @SerializedName("errorCode")
    var errorCode: String? = "",
    @SerializedName("type")
    var type: String? = ""
) : Parcelable