package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class KeywordsDisplay(
    val displayName: String = "",
    val type: String = "",
    val value: String = ""
)