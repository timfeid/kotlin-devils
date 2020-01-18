package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TeamSplit(
    val stat: TeamStat = TeamStat(),
    val team: Team = Team()
) : Parcelable