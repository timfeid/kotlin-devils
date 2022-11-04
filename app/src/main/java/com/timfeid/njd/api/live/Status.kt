package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Status(
    val abstractGameState: String,
    val codedGameState: String,
    val detailedState: String,
    val startTimeTBD: Boolean,
    val statusCode: String
)