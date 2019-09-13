package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class TimeZone(
    val id: String,
    val offset: Int,
    val tz: String
)