package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class LinescoreTeam(
    val goals: Int,
    val rinkSide: String,
    val shotsOnGoal: Int
)