package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Playback(
    val height: String? = null,
    val name: String = "",
    val url: String = "",
    val width: String? = null
) {
    companion object {
        val MOBILE = "HTTP_CLOUD_MOBILE"
    }
}