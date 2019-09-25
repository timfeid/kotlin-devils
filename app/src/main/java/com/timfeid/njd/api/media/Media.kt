package com.timfeid.njd.api.media


import com.timfeid.njd.api.content.Image
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val image: Image = Image(),
    val type: String = ""
)