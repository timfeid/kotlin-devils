package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class SecondStar(
    val fullName: String,
    val id: Int,
    val link: String
)