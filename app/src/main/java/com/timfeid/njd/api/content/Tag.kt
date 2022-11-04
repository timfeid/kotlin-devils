package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Tag(

    @SerialName("@displayName")
    val displayName: String = "",

    @SerialName("@type")
    val type: String = "",

    @SerialName("@value")
    val value: String = ""
)