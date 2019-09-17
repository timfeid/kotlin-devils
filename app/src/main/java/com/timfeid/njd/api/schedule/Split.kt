package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Split(
    val season: String,
    val stat: Stats
)