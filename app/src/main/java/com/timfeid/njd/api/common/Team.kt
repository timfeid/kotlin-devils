package com.timfeid.njd.api.common


import android.os.Parcelable
import com.timfeid.njd.api.schedule.*
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
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
    val teamStats: List<TeamStats> = listOf(),
    val roster: Roster? = null
) : Parcelable