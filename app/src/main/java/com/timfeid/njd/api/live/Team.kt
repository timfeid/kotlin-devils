package com.timfeid.njd.api.live


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class Team(
    val abbreviation: String = "",
    val active: Boolean = false,
    val conference: Conference = Conference(),
    val division: Division = Division(),
    val firstYearOfPlay: String = "",
    val franchise: Franchise = Franchise(),
    val franchiseId: Int = 0,
    val id: Int = 0,
    val link: String = "",
    val locationName: String = "",
    val name: String = "",
    val officialSiteUrl: String = "",
    val shortName: String = "",
    val teamName: String = "",
    val triCode: String = "",
    val venue: Venue = Venue()
) : Parcelable