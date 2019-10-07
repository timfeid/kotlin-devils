package com.timfeid.njd.api.schedule

import android.os.Parcelable
import com.timfeid.njd.api.live.Team
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Draft (
    val year: Int = 0,
    val round: String = "",
    val pickOverall: Int = 0,
    val pickInRound: Int = 0,
    val team: Team = Team()
) : Parcelable