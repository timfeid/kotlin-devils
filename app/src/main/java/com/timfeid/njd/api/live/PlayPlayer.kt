package com.timfeid.njd.api.live

import kotlinx.serialization.Serializable

@Serializable
data class PlayPlayer(
    val id: Int?=null,
    val fullName: String?=null,
    val link: String?=null
)
