package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class OnIcePlus(
    val playerId: Int,
    val shiftDuration: Int,
    val stamina: Int
)