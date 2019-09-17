package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class FirstStar(
    val fullName: String,
    val id: Int,
    val link: String
)