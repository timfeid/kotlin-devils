package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class About(
    val dateTime: String,
    val eventId: Int,
    val eventIdx: Int,
    val goals: Goals,
    val ordinalNum: String,
    val period: Int,
    val periodTime: String,
    val periodTimeRemaining: String,
    val periodType: String
)