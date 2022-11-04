package com.timfeid.njd.api.live


import com.timfeid.njd.api.schedule.Linescore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LiveData(
    val boxscore: Boxscore,
    val decisions: Decisions,
    val linescore: Linescore,
    val plays: Plays
)