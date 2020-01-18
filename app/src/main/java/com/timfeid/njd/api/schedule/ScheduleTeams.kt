package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ScheduleTeams(
    val away: LinescoreTeamInfo,
    val home: LinescoreTeamInfo
) : Parcelable