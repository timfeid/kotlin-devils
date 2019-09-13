package com.timfeid.njd.api.response


import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Epg(
    val platform: String? = null,
    val title: String,
    val topicList: String? = null,
    val items: List<EpgItem>
)