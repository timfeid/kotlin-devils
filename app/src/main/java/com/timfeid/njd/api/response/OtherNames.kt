package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class OtherNames(
    val firstLastNameRoman: String,
    val lastFirstName: String,
    val slug: String
)