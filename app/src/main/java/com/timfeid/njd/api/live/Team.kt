package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Team(
    val abbreviation: String,
    val active: Boolean,
    val conference: Conference,
    val division: Division,
    val firstYearOfPlay: String,
    val franchise: Franchise,
    val franchiseId: Int,
    val id: Int,
    val link: String,
    val locationName: String,
    val name: String,
    val officialSiteUrl: String,
    val shortName: String,
    val teamName: String,
    val triCode: String,
    val venue: Venue
)