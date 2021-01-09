package com.demo.networkpagination.dataModel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RowInfo(
    @SerializedName("topic")
    var topic: String? = "",
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("pictureUrl")
    var avatar: String? = "",
    @SerializedName("natives")
    var natives: List<String>? = arrayListOf(),
    @SerializedName("learns")
    var learns: List<String>? = arrayListOf(),
    @SerializedName("referenceCnt")
    var referenceCnt: Int? = 0,
) : Parcelable{
}