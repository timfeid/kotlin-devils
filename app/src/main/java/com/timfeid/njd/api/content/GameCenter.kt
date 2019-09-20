package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class GameCenter(
    val items: List<Item> = listOf(),
    val title: String = "",
    val topicList: String = ""
)