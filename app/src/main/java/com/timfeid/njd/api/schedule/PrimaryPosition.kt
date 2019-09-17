package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class PrimaryPosition(
    val abbreviation: String,
    val code: String,
    val name: String,
    val type: String
)