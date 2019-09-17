package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Venue(
    val city: String? = null,
    val id: Int? = null,
    val link: String,
    val name: String,
    val timeZone: TimeZone? = null
)