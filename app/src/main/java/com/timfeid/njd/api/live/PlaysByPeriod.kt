package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PlaysByPeriod(
    val endIndex: Int,
    val plays: List<Int>,
    val startIndex: Int
)