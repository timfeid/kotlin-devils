package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class CurrentTeam(
    val id: Int,
    val link: String,
    val name: String
)