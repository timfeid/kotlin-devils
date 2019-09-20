package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Tag(
    @Optional
    @SerialName("@displayName")
    val displayName: String = "",
    @Optional
    @SerialName("@type")
    val type: String = "",
    @Optional
    @SerialName("@value")
    val value: String = ""
)