package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Team(
    val abbreviation: String? = null,
    val active: Boolean? = null,
    val conference: Conference? = null,
    val division: Division? = null,
    val firstYearOfPlay: String? = null,
    val franchise: Franchise? = null,
    val franchiseId: Int? = null,
    val id: Int,
    val link: String,
    val locationName: String? = null,
    val name: String,
    val officialSiteUrl: String? = null,
    val shortName: String? = null,
    val teamName: String? = null,
    val venue: Venue? = null,
    val roster: Roster? = null
)