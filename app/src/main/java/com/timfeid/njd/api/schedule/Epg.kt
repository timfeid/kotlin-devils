package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Epg(
    val platform: String? = null,
    val title: String,
    val topicList: String? = null,
    val items: List<EpgItem>
) : Parcelable