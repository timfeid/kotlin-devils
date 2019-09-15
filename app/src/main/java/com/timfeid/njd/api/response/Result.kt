package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Result(
    val description: String,
    val emptyNet: Boolean,
    val event: String,
    val eventCode: String,
    val eventTypeId: String,
    val gameWinningGoal: Boolean,
    val secondaryType: String,
    val strength: Strength
)