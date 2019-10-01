package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Conference(
    val id: Int = 0,
    val link: String = "",
    val name: String = ""
)