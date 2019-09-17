package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class RadioBroadcast(
    val name: String,
    val type: String
)