package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class SecondStar(
    val fullName: String,
    val id: Int,
    val link: String
)