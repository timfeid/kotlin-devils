package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Preview(
    val items: List<Item> = listOf(),
    val title: String = "",
    val topicList: String = ""
)