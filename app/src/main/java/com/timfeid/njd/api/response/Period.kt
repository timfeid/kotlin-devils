package com.timfeid.njd.api.response


import kotlinx.serialization.Serializable

@Serializable
data class Period(
    val away: LinescoreTeam,
    val endTime: String,
    val home: LinescoreTeam,
    val num: Int,
    val ordinalNum: String,
    val periodType: String,
    val startTime: String
)