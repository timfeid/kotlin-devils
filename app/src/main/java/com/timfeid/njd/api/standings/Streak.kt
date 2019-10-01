package com.timfeid.njd.api.standings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Streak(
    val streakType: String = "",
    val streakNumber: Int = 0,
    val streakCode: String = ""
)