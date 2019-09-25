package com.timfeid.njd.api.media


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class News(
    val docs: List<Doc> = listOf(),
    val meta: Meta = Meta()
)