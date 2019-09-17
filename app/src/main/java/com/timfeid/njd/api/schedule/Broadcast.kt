package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Broadcast(
    val id: Int,
    val name: String,
    val type: String,
    val language: String
)