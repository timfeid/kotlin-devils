package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Broadcast(
    val id: Int,
    val name: String,
    val type: String,
    val language: String
)