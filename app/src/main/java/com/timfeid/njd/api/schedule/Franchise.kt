package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Franchise(
    val franchiseId: Int,
    val link: String,
    val teamName: String
)