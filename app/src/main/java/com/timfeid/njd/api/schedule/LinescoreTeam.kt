package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class LinescoreTeam(
    val goals: Int,
    val rinkSide: String,
    val shotsOnGoal: Int
)