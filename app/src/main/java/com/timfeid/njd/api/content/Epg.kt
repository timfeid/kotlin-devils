package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Epg(
    val items: List<Item> = listOf(),
    val platform: String = "",
    val title: String = "",
    val topicList: String = ""
)