package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Period(
    val away: LinescoreTeam,
    val endTime: String? = null,
    val home: LinescoreTeam,
    val num: Int,
    val ordinalNum: String,
    val periodType: String,
    val startTime: String? = null
) : Parcelable