package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class PenaltyBoxInfo(
    val active: Boolean,
    val id: Int,
    val timeRemaining: String
)