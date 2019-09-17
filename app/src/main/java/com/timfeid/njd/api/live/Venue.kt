package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Venue(
    val city: String?=null,
    val id: Int?= null,
    val link: String,
    val name: String,
    val timeZone: TimeZone?=null
)