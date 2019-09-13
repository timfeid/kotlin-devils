package com.timfeid.njd.api.response


import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Date(
    val date: String,
    val games: List<Game>,
    val totalEvents: Int,
    val totalGames: Int,
    val totalItems: Int,
    val totalMatches: Int
)