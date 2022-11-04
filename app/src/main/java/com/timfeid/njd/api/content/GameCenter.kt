package com.timfeid.njd.api.content


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class GameCenter(
    val items: List<Item> = listOf(),
    val title: String = "",
    val topicList: String = ""
) : Parcelable