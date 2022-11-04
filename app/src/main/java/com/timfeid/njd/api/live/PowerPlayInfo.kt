package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PowerPlayInfo(
    val inSituation: Boolean,
    val situationTimeElapsed: Int,
    val situationTimeRemaining: Int
)