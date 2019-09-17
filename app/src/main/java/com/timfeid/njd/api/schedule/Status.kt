package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Status(
    val abstractGameState: String,
    val codedGameState: String,
    val detailedState: String,
    val startTimeTBD: Boolean,
    val statusCode: String
)