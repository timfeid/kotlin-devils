package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class TimeZone(
    val id: String,
    val offset: Int,
    val tz: String
)