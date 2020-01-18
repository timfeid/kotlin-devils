package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class LinescoreTeam(
    val goals: Int,
    val rinkSide: String,
    val shotsOnGoal: Int
) : Parcelable