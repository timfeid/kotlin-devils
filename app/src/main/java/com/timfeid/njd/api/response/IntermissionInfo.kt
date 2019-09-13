package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class IntermissionInfo(
    val inIntermission: Boolean,
    val intermissionTimeElapsed: Int,
    val intermissionTimeRemaining: Int
)