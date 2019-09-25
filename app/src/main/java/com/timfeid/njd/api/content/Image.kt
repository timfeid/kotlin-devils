package com.timfeid.njd.api.content


import android.os.Parcelable
import android.util.ArrayMap
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Image(
    val altText: String = "",
    val cuts: Map<String, Cut> = ArrayMap<String, Cut>(),
    val title: String = ""
) : Parcelable