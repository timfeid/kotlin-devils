package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class OtherNames(
    val firstLastNameRoman: String,
    val lastFirstName: String,
    val slug: String
)