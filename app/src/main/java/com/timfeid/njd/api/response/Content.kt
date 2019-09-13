package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Content(
    val editorial: Editorial? = null,
    val highlights: Highlights? = null,
    val link: String,
    val media: Media? = null
)