package com.timfeid.njd.api.live


import com.timfeid.njd.api.common.Team
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Period(
    val away: Team,
    val home: Team,
    val num: Int,
    val ordinalNum: String,
    val periodType: String,
    val startTime: String
)