package com.timfeid.njd.api2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TeamResponse(
    val endDate: String,
    val nextStartDate: String,
    val previousStartDate: String,
    val startDate: String,
    val teams: List<Team>,
) : Parcelable {
    fun findTeamById(id: Int): Team {
        return teams.find {
            it.id == id
        }!!
    }
}

@Serializable
@Parcelize
data class Team(
    val id: Int,
    val seasonId: Long,
    val commonName: String,
    val abbrev: String,
    val name: String,
    val placeName: String,
    val logo: String,
    val isNhl: Boolean,
) : Parcelable
