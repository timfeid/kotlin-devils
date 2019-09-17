package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Goals(
    val away: Int,
    val home: Int
)