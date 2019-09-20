package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class TokenData(
    val id: String = "",
    val name: String = "",
    val position: String = "",
    val seoName: String = "",
    val teamId: String = "",
    val tokenGUID: String = "",
    val type: String = ""
)