package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class PowerPlayInfo(
    val inSituation: Boolean,
    val situationTimeElapsed: Int,
    val situationTimeRemaining: Int
)