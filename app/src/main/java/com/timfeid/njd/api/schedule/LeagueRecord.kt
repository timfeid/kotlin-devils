package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class LeagueRecord(
    val losses: Int = 0,
    val ot: Int = 0,
    val type: String = "",
    val wins: Int = 0
) : Parcelable