package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TeamStats(
    val splits: List<TeamSplit> = listOf(),
    val type: Type = Type()
) : Parcelable