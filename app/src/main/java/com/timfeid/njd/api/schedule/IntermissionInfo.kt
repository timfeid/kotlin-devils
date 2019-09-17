package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class IntermissionInfo(
    val inIntermission: Boolean,
    val intermissionTimeElapsed: Int,
    val intermissionTimeRemaining: Int
)