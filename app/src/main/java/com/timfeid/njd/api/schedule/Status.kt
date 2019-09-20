package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Status(
    val abstractGameState: String,
    val codedGameState: String,
    val detailedState: String,
    val startTimeTBD: Boolean,
    val statusCode: String
) {
    companion object {
        val GAME_STATUS_FINAL = "6"
        val GAME_STATUS_SCHEDULED = "1"
    }
}