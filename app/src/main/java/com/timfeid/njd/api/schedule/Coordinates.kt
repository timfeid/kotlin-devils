package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val x: Double? = null,
    val y: Double? = null
)