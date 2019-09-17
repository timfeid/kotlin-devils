package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Content(
    val editorial: Editorial? = null,
    val highlights: Highlights? = null,
    val link: String,
    val media: Media? = null
)