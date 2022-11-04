package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class IntermissionInfo(
    val inIntermission: Boolean,
    val intermissionTimeElapsed: Int,
    val intermissionTimeRemaining: Int
)