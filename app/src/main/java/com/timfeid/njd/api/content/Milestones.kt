package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Milestones(
    val items: List<Item> = listOf(),
    val streamStart: String = "",
    val title: String = ""
)