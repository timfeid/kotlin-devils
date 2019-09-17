package com.timfeid.njd.api.common


import com.timfeid.njd.api.schedule.*
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val abbreviation: String? = null,
    val active: Boolean? = null,
    val conference: Conference? = null,
    val division: Division? = null,
    val firstYearOfPlay: String? = null,
    val franchise: Franchise? = null,
    val franchiseId: Int? = null,
    val id: Int = 0,
    val link: String = "/",
    val locationName: String? = null,
    val name: String? = null,
    val officialSiteUrl: String? = null,
    val shortName: String? = null,
    val teamName: String? = null,
    val venue: Venue? = null,
    val roster: Roster? = null
)