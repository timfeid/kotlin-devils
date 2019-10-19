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
        val GAME_STATUS_FINAL = "7"
        val GAME_STATUS_SCHEDULED = "1"
    }

    fun isFinal (): Boolean {
        if (statusCode == GAME_STATUS_FINAL) {
            return true
        }

        return false
    }

    fun isScheduled(): Boolean {
        if (statusCode == GAME_STATUS_SCHEDULED) {
            return true
        }

        return false
    }
}