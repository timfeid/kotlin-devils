package com.timfeid.njd.api.media


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Tag(
    val displayName: String = "",
    val type: String = "",
    val value: String = ""
)